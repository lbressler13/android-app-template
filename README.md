# Android App Template

[![All Checks](https://github.com/lbressler13/android-app-template/actions/workflows/all_checks.yml/badge.svg?branch=main)](https://github.com/lbressler13/android-app-template/actions/workflows/all_checks.yml)

## Overview

This is a template for creating an Android app with two build flavors. 
It contains a pre-made fragment to list icons that are used in the app and a basic developer tools dialog. 
This dialog can be expanded with app-specific functionality, such as erasing data from ViewModels.

### Build Flavors

The template is set up with 2 builds flavors: a dev flavor, and a final flavor. 
The developer tools dialog is only available in the dev variant. It can be accessed via the icon in the bottom left corner of the screen.

See [here](https://developer.android.com/studio/build/build-variants) for information about configuring build variants in an Android app.

### Dependencies

The build files have a dependency on a package that is hosted in the GitHub Packages registry.
To pull packages from the registry, you need a GitHub access token with the `read:packages` scope.
**Do not commit your access token.**

You can add the following properties to a gradle.properties file:
```properties
gpr.user=GITHUB_USERNAME
gpr.key=GITHUB_PAT
```
or to the environment in the terminal:
```shell
USERNAME=GITHUB_USERNAME
TOKEN=GITHUB_PAT
```

See [here](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package) for more information on importing GitHub packages.

## Testing

### Unit Tests

Unit tests can be run via an IDE, or with the following command:
```./gradlew test```

### Integration Tests

Sample integration tests are implemented using the Espresso framework.
Tests can be configured for an individual build flavor, or can be run against both flavors.
The template has pre-existing tests for the image attributions fragment and developer tools dialog.

Tests can be run via an IDE, or with the following commands:
* All tests for both flavors: `./gradlew connectedCheck`
* Dev build flavor only: `./gradlew connectedDevDebugAndroidTest`
* Final build flavor only: `./gradlew connectedFinalDebugAndroidTest`

Espresso tests must be run on a physical device or an emulator.
See [here](https://developer.android.com/training/testing/espresso) for more information about testing with Espresso.

## Linting

Linting is done using [ktlint](https://ktlint.github.io/), using [this](https://github.com/jlleitschuh/ktlint-gradle) plugin.
See [here](https://github.com/pinterest/ktlint#standard-rules) for a list of standard rules.

To run linting and fix formatting issues if possible, run the following command in the terminal or via an IDE:
```./gradlew ktlintFormat```

To run linting without fixing issues, run the following command in the terminal or via an IDE:
```./gradlew ktlintCheck```

## Using the Template

### Package Name

The package name can be updated using Android Studio.
To change it, find the package name in the under the project structure.
Right-click on the name, then select Refactor > Rename > In Whole Project. 
This will change the name for all tests and build flavors.

The name also needs to be changed in the following places:
- [ ] App name in the main and dev string resources files. These values will be used in the app manifest.
- [ ] Action bar title in the main string resources file.
- [ ] Theme name in the day and night theme resource files. The theme also needs to be updating in the app manifest.
- [ ] Application name and namespace in the app build.gradle.kts file
- [ ] Root project name in settings.gradle.kts

### Workflows

All three workflows use gradlew to execute commands.
You may need to run `git update-index --chmod=+x ./gradlew` to grant executable permissions in GitHub actions.

The template contains 3 workflows. The first workflow runs basic checks, such as linting and unit tests. 
The second workflow runs Espresso tests, and the third workflow runs both the first and second one.
The third workflow is triggered automatically on merges to the main branch. 
However, Espresso tests use a large number of GitHub actions minutes, due to the amount of time they take and the OS they run on.
If there are restrictions on GitHub actions minutes, you should consider removing the Espresso step or disabling the automatic trigger for that workflow.

## Folder Structure

```project
├── app
│   ├── src
│   │   ├── dev                       <-- code and resources that are specific to dev product flavor
│   │   ├── espresso                  <-- UI tests
│   │   ├── espressoDev               <-- UI tests that are specific to dev product flavor
│   │   ├── espressoFinal             <-- UI tests that are specific to final product flavor
│   │   ├── final                     <-- code and resources that are specific to final product flavor
│   │   ├── main
│   │   │   ├── kotlin                <-- main source code
│   │   │   ├── res                   <-- app resources, including strings, layouts, and images
│   │   │   ├── AndroidManifest.xml   <-- app manifest file
│   │   ├── test                      <-- unit tests
│   ├── build.gradle.kts              <-- module level gradle file, contains app dependencies
├── build.gradle.kts                  <-- project level gradle file
├── gradle.properties
├── README.md
└── settings.gradle
```

## Image Attributions

All images are taken from [Flaticon](https://www.flaticon.com/), which allows free use of icons for personal and commercial purposes with attribution.
This is the complete list of Flaticon images used within the app.
The list is also available within the app itself.

| Icon                                                        | Creator                                                                                                                    | Link                                                                  |
|:------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------|
| ![img](app/src/main/res/drawable-hdpi/ic_chevron_down.png)  | Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from <https://www.flaticon.com>                           | <https://www.flaticon.com/premium-icon/down-chevron_1633716>          |
| ![img](app/src/main/res/drawable-hdpi/ic_chevron_left.png)  | Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from <https://www.flaticon.com>                           | <https://www.flaticon.com/premium-icon/left-chevron_1633718>          |
| ![img](app/src/main/res/drawable-hdpi/ic_chevron_right.png) | Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from <https://www.flaticon.com>                           | <https://www.flaticon.com/premium-icon/right-chevron_1633719>         |
| ![img](app/src/main/res/drawable-hdpi/ic_chevron_up.png)    | Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from <https://www.flaticon.com>                           | <https://www.flaticon.com/premium-icon/up-chevron_1633717>            |
| ![img](app/src/main/res/drawable-hdpi/ic_close.png)         | Icon made by [Ilham Fitrotul Hayat](https://www.flaticon.com/authors/ilham-fitrotul-hayat) from <https://www.flaticon.com> | <https://www.flaticon.com/premium-icon/cross_4421536>                 |
| ![img](app/src/main/res/drawable-hdpi/ic_info.png)          | Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from <https://www.flaticon.com>                           | <https://www.flaticon.com/free-icon/info-button_64494>                |
| ![img](app/src/main/res/drawable-hdpi/ic_settings.png)      | Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from <https://www.flaticon.com>                           | <https://www.flaticon.com/premium-icon/gear_484613>                   |

See [here](https://support.flaticon.com/s/article/Attribution-How-when-and-where-FI?language=en_US&Id=ka03V0000004Q5lQAE) for more information about Flaticon attributions.
