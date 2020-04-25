# 창의학기제 모바일(5주차)

### 학습내용
#### SharedPreferences 등 어플 내에서 데이터 저장방법들을 공부하였다.

##### 사용한 API 및 라이브러리
- RecyclerView
- SharedPreferences
- Gson

##### RecyclerView & Gson 사용방법
- [RecyclerView jetpack](https://developer.android.com/jetpack/androidx/releases/recyclerview)를 참고하여 진행. 
  
  기존에 사용하던 [support 패키지를 사용한 방식](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=ko)은
  안드로이드 버전이 올라감에 따라 사용이 힘듦.
- [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences?hl=ko)
- [Gson 깃헙주소](https://github.com/google/gson)를 참고하여 진행함. 

##### 안드로이드 데이터 저장방법.
- 안드로이드 앱에서 데이터를 저장하는 대표적인 방법으로는 크게 3가지가 있다.
    - Preferences(프레퍼런스)
    
      키와 값의 쌍 (Key-Value pair)을 저장하고 가져오는 가벼운 메커니즘.
    - File(파일)
    
      모바일 디바이스 상에서 또는 분리될 수 있는 저장매체
    - Databases(데이터베이스)
    
      안드로이드 API는 SQLite 데이터베이스를 생성, 사용 등을 지원하는데, 마찬가지로 데이터베이스를 생성한 어플리케이션에서만 접근 가능함.
      SQLiteDatabase 오브젝트는 데이터베이스를 나타내고, 이것과 상호작용하는 쿼리와 메소드들을 가지고 있음. 
     
    
- 이 중 가볍게 사용가능하고 방법이 어렵지 않은 SharedPreference를 사용하기로 했다.

※ SharedPreference는 전형적으로 애플리케이션이 시작될 때마다 로드되어야 하는 기본적인 환영인사말, 폰트, 자동로그인 저장 등의 환경설정 정보 등을 가져오며, 또한 약간의 데이터들만 저장할 때에도 주로 사용된다.


##### 발생한 문제

- SharedPreference는 

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

