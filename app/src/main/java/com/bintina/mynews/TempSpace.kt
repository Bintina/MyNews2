package com.bintina.mynews



class TempSpace {
    // TODO: Consider calling
    //    ActivityCompat#requestPermissions in NotificationWorker
    // here to request the missing permissions, and then overriding
    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
    //                                          int[] grantResults)
    // to handle the case where the user grants the permission. See the documentation
    // for ActivityCompat#requestPermissions for more details.
}
//rerouting Science to top xml (recycler view)
//CRASH

/*
Build cleaned and built


  static-code-analysis:
    needs: [ instrumentation-test ]
    runs-on: ubuntu-latest
    steps:
      - name: Checkout the code
        uses: actions/checkout@v2

      - name: set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: 11

      - name: SonarCloud Scan
        run: ./gradlew app:sonarqube -Dsonar.login=32b22dfae75a71fbabd586bb34433fd9430ceabc
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
*/
