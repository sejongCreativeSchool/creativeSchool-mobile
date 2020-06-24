# 창의학기제 모바일(6주차)

### 학습내용

#### 학습 주제
프로젝트 설계 : 소프트웨어 디자인패턴에 대해 알아보고, 각 패턴별 장단점을 분석한 뒤 프로젝트에서 진행할 패턴을 결정함.
<br/>

#### 소프트웨어 디자인패턴

소프트웨어 디자인패턴을 문장으로 정리하자면, 개발을 할 때 특정 문맥에서 공통적으로 발생하는 문제에 대해 재사용이 가능한 해결책/형식화된 관행이고, 바로 사용할 수 있는 알고리즘 코드 같은 게 아니라 특정한 상황에서 구조적인 문제를 해결하는 방식을 의미한다. 

이는 다음과 같은 장점을 가져온다.
- 개발자간 원활한 의사소통 도모.
- 신속한 소프트웨어 구조 파악 및 구조 변경 용이.
- 재사용이 가능한 코드구조.
<br/>
디자인 패턴을 알기 전, 모바일 앱 아키텍처에 대해 짚고 넘어가자.

- 사용환경 중단 : 
<br/> 모바일 환경에서는 전화, 알림 등에 의해 사용 환경이 중단되거나 다른 앱으로 바꾸는 동작이 일반적이다. 사용자는 사용 환경 중단에 대응하고 다시 앱으로 돌아가기를 기대하기 때문에 앱에서 이러한 흐름을 올바르게 처리할 필요가 있다.

- 휴대기기의 제한적 리소스 : 
<br/>운영체제에서 새로운 앱을 위한 공간 확보를 위해 언제든 앱 프로세스의 종료가 가능하다.<br/>


정리하자면, 앱 구성요소는 개별적이고 비순차적으로 실행이 가능하고, 사용자나 운영체제에 의해 언제든지 제거될 수 있다. 그렇기 때문에 앱 구성요소에 앱 데이터나 상태 등을 저장해서는 안되고 앱 구성요소는 서로 종속되면 안되는 것이다.

<br/>
또한, 일반 아키텍처의 원칙으로는

- 관심사 분리 (Separate of Concerns) :
<br/>OS는 언제든지 이 클래스들을 제거할 수 있다. 사용자 환경과 수월한 앱 관리 환경을 위해 이러한 클래스의 의존성을 최소화하는 것이 필요하다.
- 모델에서 UI 만들기 : 
<br/>모델이란 앱의 데이터 처리를 담당하는 구성요소로 View 개체와 앱 구성요소에 독립되어 앱 수명주기에 영향을 받지 않는다. 안드로이드 개발자가이드에서는 OS에서 앱을 제거해도 사용자 데이터가 삭제되지 않고, 네트워크 연결이 약하거나 연결되지 않아도 앱이 작동해야 함을 이유로 지속모델을 권장한다.

<br/><img width="400" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/45682868/85535754-716e1c00-b64d-11ea-8288-a6710f47a51f.png">

이벤트 리스너, 데이터 처리 등 모든 것이 한 액티비티에 존재하는 것을 확인할 수 있다. 이렇게 코드를 작성하게 되면 시간이 지날수록 복잡, 비대해져 문제가 생길 수 있고, 유지보수에 어려움이 있을 수 있다. 또한 액티비티 특성상 수명주기에 따른 영향도 있을 것이며 데이터도 안전하게 다루지 못하게 된다.
결국 모바일 앱 사용 환경, 안드로이드 클래스의 특징, 앱 수명주기의 영향, 코드의 복잡성 등 다양한 이유와 문제점을 바탕으로 좀 더 안전하고 깔끔한 개발을 위해 아키텍처 패턴이 생겨나게 된 것이다.



#### MVC패턴
MVC패턴은 모델-뷰-컨트롤러 방식으로, 사용자 인터페이스와 비즈니스 로직을 분리해서 어플리케이션의 시각적 요소나 그 이면에서 실행되는 비즈니스 로직을 서로 영향없이 쉽게 고칠 수 있는 모델이다.

1.	모델 (비즈니스 로직)
	- 	어떤 동작을 수행할 수 있는 코드
	- 	뷰나 컨트롤러에 매여있지 않아서 재사용 가능
2.	뷰 (사용자 인터페이스)
 	-	모델로부터 값을 가져와서 사용자에게 보여줌
3.	컨트롤러
 	-	모델과 뷰를 이어주는 접착제같은 역할 Activity
	-  어떻게 상호작용할 것인지 결정 (버튼을 누르면? 어떻게 정보를 업데이트 할 것인지?)MVC 컨트롤러 문제점

- 문제점

	모델과 뷰를 분리하고, 모델의 비종속성으로 재사용이 가능하며, 구현하기 가장 간편하다는 장점을 가지고 있지만, 뷰와 모델 사이 의존성이 높다. 시간이 지날수록 컨트롤러에 많은 코드가 쌓여 비대해지고 유지보수가 어렵다. 

	아래 예시를 보면, View에 상관없는 로직을 MainModel로 분리하였기 때문에 Activity는 View와 ClickEvent를 처리하는 모습으로 변화되었다. 하지만 Android 특성상 Activity에서 View와 Controller 두가지 특성을 모두 가지고 있기 때문에, View나 Vontroller를 한쪽으로 빼개 되면 View에 대한 바인딩이나 처리에서 중복코드을 가지고 일관성을 잃게된다

```java
	public class MainActivity extends Activity{
		private MainModel mainModel;

		@Override
		public void onCreate(Bundle saveInstance){
			super.onCreate(saveInstance);
			setContente(R.layout.main);
			
			mainModel = new MainModel();
			
			TextView textView = (TextView)findViewById(R.id.btn_confirm);
			textView.setTextView("Non-Clicked");

			findViewById(R.id.btn_confirm)
				.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View view){
						String text = mainModel.getClickedText();
						TextView textView = (TextView)findViewById(R.id.#);
						textView.setText(mainModel.getClickedText());
					});
		}
	}
```
```java
	public class MainActivity extends Activity{
		public String getClickedText(){
 				return "Clicked!!";
		}
	}
```


#### 발생한 문제

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

<img width="400" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/45682868/85535754-716e1c00-b64d-11ea-8288-a6710f47a51f.png">

<Retrofit에 관한 예제이기에 수량체크는 하지 않음>

##### Purpose
안드로이드에서 Retrofit을 사용법을 알아보기 위해 공적마스크 지도 어플 제작 프로젝트를 시작하였음.

##### Language
: JAVA (Android)

##### Comments
- TedPermission을 사용하지 못하더라도 퍼미션체크를 할 수 있도록 안드로이드에서 제공하느 기본 퍼미션 체크를 더 연습해봐야겠다.  
- 레트로핏 관련하여 공식문서와 작성법 등을 더 배워야겠다.  
##### Study Result

