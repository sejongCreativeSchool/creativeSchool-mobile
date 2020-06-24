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



#### **MVC패턴**
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


#### **MVP모델**

<br/><img width="400" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/45682868/85539698-19391900-b651-11ea-95ca-faf1944326ac.png">

- 모델-뷰-프리젠터 구조이며, 컨트롤러와 달리 프리젠터는 종속되어 있지 않아도 모델과 뷰를 연결시켜서 MVC 패턴이 가지고 있던 문제점을 일부 해소한다. 특징은 다음과 같다.

1. 모델
	- MVC와 동일
2. View
	- 사용자에게 제공되는 UI
	- 액티비티/프래그먼트가 뷰의 일부로 간주
	- 사용자의 입력을 받고 이벤트를 프리젠터로 전달
3. Presenter
	- 모델과 뷰 상호작용 관리
	- 컨트롤러와 본질적 동일. 하지만, 뷰에 연결되지 않는 단순 인터페이스
	- 뷰에게 표시할 내용만 전달 (표시방법은 지시하지 않음)

**presenter**

```java
	public Interface MainActivity{
		void confirm();
		interface View{
			void setText(String text);
		}
	}
```
**View**

```java
	public class MainActivity implementes Presenter.View{
		private Presenter presenter;
		private TextView textView;

		protect void onCreate(#####){
			super.onCreate(saveInstance);
			setContente(R.layout.main);

			presenter = new PresenterImpl();

			textView.setonClicklistener(##presenter confirm());
			
		}
	}
```
**presenterImpl**

```java
	public Interface MainActivity{
		void confirm(){
			if(view != null)
				view.settext(###)
		}
	}
```

View에 대한 직접적인 접근이 요구되는 Android의 Activity는 직접적인 view 접근은 Activity 가 하도록 하고 이에 대한 제어는 Presenter 가 하도록 하고 있다. MVP 패턴에서 모델과 뷰는 MVC 패턴에서와는 달리 더이상 서로 간의 의존성이 존재하지 않는다. 모델은 프리젠터의 요청만 수행하면 되므로 다른 부분과의 상호작용은 전혀 신경쓰지 않아도 된다. 하지만 프리젠터도 시간이 지날수록 코드가 쌓여 비대해지게 되어 유지보수에 어려움이 있을 수 있다. 그리고 프리젠터를 구현하기 위해 인터페이스와 인터페이스 구현체를 구현해야 하는 등 MVC에 비해서 필요한 클래수 수가 증가하게 된다. 또한 뷰와 프리젠터의 1:1 관계로 인해 서로 간 의존성이 커지게 된다는 단점이 있다.

<br/>


#### **MVVM모델**

<br/><img width="400" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/45682868/85550267-c1ec7600-b65b-11ea-851d-247ccdb3464b.png">


사용자 입력은 이제 뷰를 통해 들어오게 된다. 뷰는 이러한 이벤트를 프리젠터로 전달하고 프리젠터는 모델과의 상호작용을 통해 뷰에게 업데이트 할 내용을 전달한다. 그리고 이 내용을 받은 뷰가 최종적으로 업데이트 된다. 

특징은 다음과 같다.

1.  Model
	- MVC와 동일
2. View
	- 사용자에게 제공되는 UI
	- 사용자의 입력을 받고 이벤트를 자신이 사용할 뷰모델로 전달
3. ViewModel
	- 뷰를 나타내주기 위한 모델 + 뷰의 표현 로직 담당
	- 뷰와 독립적
	- UI 관련 데이터 보관, 관리
	- 모델이 변경되면 관련된 뷰모델을 사용하는 뷰가 자동 업데이트


<br/><img width="400" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/45682868/85550573-0f68e300-b65c-11ea-81bb-b10dcf8539da.png">

하지만 안드로이드에서 MVVM이 가지는 문제점은 View에 대한 처리가 복잡해질수록 ViewModel에 거대해지게 되고 상대적으로 Activity는 아무런 역할도 하지 않는 형태의 클래스로 변모하게 되었다. Controller의 성격을 지닌 Activity 가 실질적으로 아무런 역할을 하지 못하고 ViewModel에 치중된 모습을 보여줌으로써 다른 형태의 Activity 클래스를 구현한 꼴이 되어버린다. MainViewModel에 있는 로직을 다시 Activity 로 롤백한다하면 다시 MVC 가 가지고 있는 문제점을 가지게 되는 아이러니한 모습을 가지게 되었다.


#### 결과 
지금까지의 프로젝트는 패턴을 생각하지 않고 만들었다. 어플의 크기도 작고 복잡하지 않아서 액티비티에 함수와 기능들을 모아 작성하였는데, 이번 프로젝트부터는 패턴에 맞추어 개발하고자 한다.

이번에 사용해볼 패턴은 MVP 패턴으로, MVP패턴을 이용해서 이와 같이 Model과 View간의 결합도를 낮추면, 새로운 기능을 추가하거나 변경할 필요가 있을 때 관련된 부분만 수정하면 되기 때문에 확장성이 좋아지며, 테스트 코드를 작성하기 편리해지기 때문에 더 안전한 코드 작업이 가능해지기에 선택했다.



