# 창의학기제 모바일(4주차)

##### 4주차 폴더 및 4-2주차 폴더, 총 APP 2개

### 학습내용

##### 사용한 API
- Firebase cloud messaging


##### Firebase cloude messaging (이하 FCM) 사용방법
[FCM 안드로이드 사용 가이드](https://firebase.google.com/docs/cloud-messaging/android/client)를 참고하여 진행함. 


##### FCM이란?
- FCM을 사용하면 새 이메일이나 기타 데이터를 동기화할 수 있음을 클라이언트 앱에 알릴 수 있다.
- 이렇게 알림 메시지를 전송하여 사용자를 유지하고 재참여를 유도할 수 있다.
- 채팅 메시지와 같은 사용 사례에서는 메시지로 최대 4KB의 페이로드를 클라이언트 앱에 전송할 수 있다.
- IOS, Android, Web등 기존에 메시지를 발송하려면 APNS, GCM 등 각각 환경별로 개발해야하는 불편함이 있었는데, FCM을 이용하면 이러한 부분을 한번에 처리할 수 있다. 


##### 사용방법
- 안드로이드에서 FCM을 설정하려면 app수준 Gradle파일에 추가한다.
```java
implementation 'com.google.firebase:firebase-messaging:20.0.0'
```

- 앱 메니페스트에 다음을 추가하여야 한다.

  FirebaseMessagingService를 확장하는 서비스를 추가한다. 백그라운드에서 앱의 알림을 수신하는 것 외에 다른 방식으로 메시지를 처리하려는 경우에 필요하다.

  포그라운드 앱의 알림 수신, 데이터 페이로드 수신, 업스트림 메시지 전송 등을 수행하려면 이 서비스를 확장해야 한다.
  
  ※쉽게 설명하자면, MyFirebaseMessagingService라는 클래스에 메세징이벤트(알림 수신 등)을 수행하겠다는 뜻이다. 
    <span style="color:red"> MyFirebaseMessagingService라는 클래스이름을 사용하거나 표시부분의 이름을 해당 클래스 이름으로 하여야 한다. </span>
```java
<service
    android:name=".java.MyFirebaseMessagingService" <!-- 표시 -->
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
}
```

- (선택사항) Android 8.0(API 수준 26) 이상부터는 알림 채널이 지원 및 권장된다.

  FCM은 기본적인 설정으로 기본 알림 채널을 제공하는데, 기본 채널을 직접 만들어 사용하려면 아래와 같이 default_notification_channel_id를 알림 채널 객체의 ID로 설정한다. 
  
  받은 메시지에 명시적으로 설정된 알림 채널이 없으면 FCM에서는 항상 이 값을 사용한다.
  
  ※실습 진행시 느낀 바로는, Android Oreo 이상 기기를 지원하려면 oreo이상 기기에서는 채널을 써야한다.
  
    이번주차에서는 알림채널을 설정하지 않아 Galaxy Note 10 5G에서 포그라운드 알림메세지를 받지 못했다.
```java
<meta-data
    android:name="com.google.firebase.messaging.default_notification_channel_id"
    android:value="@string/default_notification_channel_id" />
}
```

- 다음으로는 등록 토큰 검색하고 설정한 뒤, [onMessageReceived](https://firebase.google.com/docs/cloud-messaging/android/topic-messaging?authuser=0)를 설정하면 된다.


##### 요약
- FCM을 사용하여 백그라운드로 알림을 받으려면 Gradle 추가, SDK설정, 토큰액세스를 진행하면 된다.
[백그라운드 앱에 테스트 메시지 보내기](https://firebase.google.com/docs/cloud-messaging/android/first-message?hl=ko)
- 포그라운드에서 알림을 받으려면 onMessageReceived를 재정의해야 한다.
[포그라운드 앱에서 메시지 수신하기](https://firebase.google.com/docs/cloud-messaging/android/receive?hl=ko)


##### 발생한 문제

- 4주차에서는 포그라운드에서 데이터를 수신하였지만 알림설정이 되지 않았다. 위에서 말한대로 알림채널 설정이 안되어서이다. 
  4-2 코드에서는 따로 안드로이드 버전에 따라 채널을 설정해주는 코드를 삽입하였다.

#### 코드 추가 예정 ( 노트북 수리 중 )


### FCM을 이용한 알림수신이 가능한 간단한 채팅 APP

-  ※알림수신 공부를 위한 APP이므로 디자인, 채팅 상세기능 배제.

<!--
<img width="400" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/45682868/79812004-88c12c80-83b2-11ea-9190-e5a519cce23a.jpeg">
-->
#### 사진 및 설명 추가 예정 ( 노트북 수리 중 )


##### Purpose
안드로이드에서 FCM 사용법 및 상황별 수신방법을 알아보기 위해 메시지 수신 가능한 APP 제작.

##### Language
: JAVA (Android)


##### Comments
- 안드로이드버전에 따른 설정법을 다시 익혀야겠다.
- 주 프로젝트 전 알림채널 설정에 대해 다시 배워야겠다. 지금은 공식문서를 따라 만드는 수준인 것 같다.
- FCM 발신방법에 대해 주 프로젝트 전 배워야겠다.
##### Study Result

