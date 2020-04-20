# 창의학기제 모바일(3주차)

### 학습내용
##### 사용한 API
- T Map API
- Retrofit2

##### T Map API 사용방법
[T Map API 사용 가이드](http://tmapapi.sktelecom.com/main.html#android/guide/androidGuide.sample1)를 참고하여 진행함. 

##### Retrofit이란?
Retrofit은 HTTP API를 자바 인터페이스 형태로 사용 가능한 typesafe한 http클라이언트 라이브러리이다.
네트워크로 전달받은 데이터를 필요한 형태의 객체로 저장 가능하다.
```

```

##### 발생한 문제

- Kakao Map API는 Script 태그 형태로 불러와서 사용하는데, React 컴포넌트에서 사용하는 방법을 몰랐다.   
- 아래는 Kakao Map API Docs에서 제공하는 맵 띄우는 예시 코드이다.
```java
<html>
<head>
	<meta charset="utf-8"/>
	<title>Kakao 지도 시작하기</title>
</head>
<body>
	<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 넣으시면 됩니다."></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
</body>
</html>
```
이거를 React에 적용하는 방법을 몰라 크게 애를 먹었다.   
내가 해결한 방법은 다음과 같다.   
- 우선 public/index.html 의 head 태그의 자식 요소로 
    ```
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 넣으시면 됩니다."></script>
    ```
    를 넣었다.
- 이 후, Map을 보여주는 Component인 src/components/KakaoMap.js 를 만들고 다음과 같이 코드를 작성하였다.   
```
import React, { useEffect, useRef } from "react";
const { kakao } = window; // window에 존재하는 kakao를 비구조화 할당해준다.

function KakaoMap() {
  const mapContainer = useRef(); // id가 Map인 div태그의 DOM 을 직접 건드리기 위해 useRef Hooks를 사용하였다.
  useEffect(() => {
    kakao.maps.load(() => {
      let el = mapContainer.current; // 변수 el에 id가 "map"인 DOM 요소를 할당해 준다.
      let map = new kakao.maps.Map(el, {
        center: new kakao.maps.LatLng(37.549503, 127.075174)
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      markers.map(marker => marker.setMap(map));
    });
  }, []);

  return (
    <div
      id="map"
      style={{
        width: "700px",
        height: "630px",
        ref={mapContainer} // DOM을 직접 조작하기 위해 ref 속성을 추가해준다.
    ></div>
  );
}
export default KakaoMap;

```
이런식으로 해주니 Kakao Map이 떴다.   
추후, JavaScript Window 객체에 대해 추가적인 공부를 진행할 예정이다.

### Kakao Map API를 이용한 세종대 근처 맛집 WEB APP

<img width="1330" alt="스크린샷 2020-03-31 오후 8 36 44" src="https://user-images.githubusercontent.com/52201658/78022334-b99fda00-738f-11ea-9ec4-1762e4443d1e.png">


##### Purpose
React에서 외부 Script 형식의 API를 사용하는 것을 경험하기 위해 프로젝트를 시작했습니다.

##### TechSet
Language: Javascript ES6, JSX   
Library: React, Redux, Styled-Components, Kakao Map API 

##### Comments
- 순수한 자바스크립트(Vanilla JS)로 하는 프론트엔드 개발을 거치지 않고, 리액트로 프론트엔드 개발을 시작해 기본적인 JavaScript 웹 개발 개념이 부족한거 같다.   
- 추후에, 순수한 자바스크립트로(Vanilla JS)로 프론트엔드 개발을 진행해봐야 겠다고 느꼈다.   
- 지난 주에도 느꼈지만, CSS기초가 너무 부족한 것 같다. 빨리 다음주가 되서 CSS 공부를 진행하고 싶다고 느꼈다.    
- 확실히 리액트, 리덕스가 많이 익숙해 진 것 같다. 이제 단순 구현을 넘어서 최적화 진행에도 관심을 가져야겠다.   
##### Study Result

