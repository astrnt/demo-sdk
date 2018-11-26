### Astronaut Manager SDK for Android
Currently we just support SDK for Video Interview

##### Make sure you register as Developer Partner at Astronaut. If you haven't, you can register [here](https://developers.astrnt.co/)
---
Requirements :
* minSdkVersion 21
---
#### Setup SDK

1. Add the JitPack repository to your build file
Add it in your root **build.gradle** at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. Add the dependency
check the version [here](https://github.com/astrnt/manager-sdk/releases)

```
	dependencies {
	        implementation 'com.github.astrnt:manager-sdk:latest-version'
	}
```

3. Add this line in **app/build.gradle**
```
    flavorDimensions "mode"
    productFlavors {
        beta {
            dimension "mode"
            applicationIdSuffix ".betasdk"
            buildConfigField "boolean", "BETA", "true"
            buildConfigField("String", "API_URL", '"http://beta.astrnt.co/api/v2/"')
            buildConfigField("String", "API_KEY", '"YOUR_API_KEY_HERE"')
            buildConfigField ("int", "SDK_VERSION", "1")
        }
        live {
            dimension "mode"
            buildConfigField "boolean", "BETA", "false"
            buildConfigField("String", "API_URL", '"http://app.astrnt.co/api/v2/"')
            buildConfigField("String", "API_KEY", '"YOUR_API_KEY_HERE"')
            buildConfigField ("int", "SDK_VERSION", "1")
        }
    }
```

4. SetUp SDK in your **Application Class**
```
    private static ManagerSDK managerSDK;

    public static AstronautApi getApi() {
        return managerSDK.getApi();
    }

    private void setUpSDK() {
        if (managerSDK == null) {
            managerSDK = new ManagerSDK(this, BuildConfig.API_URL, BuildConfig.DEBUG, BuildConfig.API_KEY);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setUpSDK();
    }
```

##### For more detail you can see our [sample here.](https://github.com/astrnt/manager-sdk/tree/master/sample)
---
##### Supports
Sorry, we no longer answer question from repository issues. If you need any assistance, please contact developers@astrnt.co
