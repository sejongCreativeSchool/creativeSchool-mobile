# 창의학기제 모바일(3주차)

### 학습내용

##### 사용한 API
- T Map API
- Retrofit2
- TedPermission

##### T Map API 사용방법
[T Map API 사용 가이드](http://tmapapi.sktelecom.com/main.html#android/guide/androidGuide.sample1)를 참고하여 진행함. 

##### Retrofit이란?
Retrofit은 HTTP API를 자바 인터페이스 형태로 사용 가능한 typesafe한 http클라이언트 라이브러리이다.
네트워크로 전달받은 데이터를 필요한 형태의 객체로 저장 가능하다.
```java
public interface GitHubService{
	@GET("/users/{user}/repos")
	Call<List<Repo>> listRepo(@Path("User") String user);
}
```

Retrofit 클래스로 인터페이스를 구현하여 생성한다.
```java
Retrofit retrofit = new Retrofit.Builder()
	.baseUrl(""https://8oi9s0nnth.apigw.ntruss.com"") // 이 주소에 서버 주소가 들어감
	.build();
}
```

##### 발생한 문제

-레트로핏 사용법을 알기 위해선 HTTP 등의 이해가 더 필요했다. 실 프로젝트 진행 전까지 더 공부할 예정.

-Tmap 관련하여 문제는 발생하지 않음.

- 현재위치를 알기 위해서는 GPS와 네트워크로 현재위치를 받아온다는 퍼미션을 하여야 한다.

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```
현재위치는 개인정보보호를 위해 앱 첫 실행시, 경우에 따라선 해당 퍼미션을 수행해야 할 때마다 사용자에게 허가를 받아야 한다.
안드로이드에서 기본 퍼미션 받는 방법은
```java
 // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(thisActivity,
            Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                Manifest.permission.READ_CONTACTS)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(thisActivity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    } else {
        // Permission has already been granted
    }
    
```
응답처리는 다음과 같다.
```java
   @Override
    public void onRequestPermissionsResult(int requestCode,
            String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

```
그러나 매번 응답처리도 힘들었고, OS와 퍼미션에 따라 조금씩 달라 애를 먹었다.

그래서 찾은 것이 TedPermission이다.
사용법은 다음과 같다.
```java
 TedPermission.with(this)
                .setPermissionListener(permissionlistener) // 리스너 아래 참고
                .setRationaleMessage("현재위치 정보를 받기 위해서는 위치정보 접근 권한이 필요해요") //퍼미션을 받는 이유
                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.") //퍼미션 거부 시 메세지
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET) // 퍼미션 종류
                .check();
```

위에서 퍼미션 권한 허용 여부에 따라 리스너에서 처리한다.

```java
 PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(CheckPermission.this, "권한 허가", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(CheckPermission.this, MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(CheckPermission.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
```

### T Map API와 Retrofit을 이용한 공적마스크 지도 APP

<img width="400" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/45682868/79812004-88c12c80-83b2-11ea-9190-e5a519cce23a.jpeg">

<Retrofit에 관한 예제이기에 수량체크는 하지 않음>

##### Purpose
안드로이드에서 Retrofit을 사용법을 알아보기 위해 공적마스크 지도 어플 제작 프로젝트를 시작하였음.

##### Language
: JAVA (Android)

##### Comments
- TedPermission을 사용하지 못하더라도 퍼미션체크를 할 수 있도록 안드로이드에서 제공하느 기본 퍼미션 체크를 더 연습해봐야겠다.  
- 레트로핏 관련하여 공식문서와 작성법 등을 더 배워야겠다.  
##### Study Result

