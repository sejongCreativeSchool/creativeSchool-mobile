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


##### 외부 라이브러리 없이 string 기반 ArrayList 저장 방법

[ArrayList<string> -> json](https://codechacha.com/ko/sharedpref_arraylist/)

- Preference는 키와 값 쌍으로 저장되는데, 객체나 배열은 저장하지 못한다. 
- 간단한 저장이어서 DB사용이 애매할 때, 배열을 저장할 수 있다.

```java
private void setStringArrayPref(Context context, String key, ArrayList<String> values) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    JSONArray a = new JSONArray();
    for (int i = 0; i < values.size(); i++) {
        a.put(values.get(i));
    }
    if (!values.isEmpty()) {
        editor.putString(key, a.toString());
    } else {
        editor.putString(key, null);
    }
    editor.apply();
}

private ArrayList<String> getStringArrayPref(Context context, String key) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    String json = prefs.getString(key, null);
    ArrayList<String> urls = new ArrayList<String>();
    if (json != null) {
        try {
            JSONArray a = new JSONArray(json);
            for (int i = 0; i < a.length(); i++) {
                String url = a.optString(i);
                urls.add(url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    return urls;
}

```


##### Gson을 사용하여 string 기반 ArrayList 저장 방법

앱 수준 gradle에 아래와 같이 추가한다.

```xml
implementation 'com.google.code.gson:gson:2.8.5'

```

아래 코드를 사용하여 저장한다.
```java
SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);

//export
MyObject myObject = new MyObject;
//set variables of 'myObject', etc.

Editor prefsEditor = mPrefs.edit();
Gson gson = new Gson();
String json = gson.toJson(myObject);
prefsEditor.putString("MyObject", json);
prefsEditor.commit();
//import
Gson gson = new Gson();
String json = mPrefs.getString("MyObject", "");
MyObject obj = gson.fromJson(json, MyObject.class);

```


##### Purpose
- 간단하고 가벼운 저장방법을 습득하고, 배열을 Preference로 저장하는 법을 배운다.

##### Language
: JAVA (Android)

##### Comments
- 없음
 
##### Study Result

