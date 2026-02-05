ISSUE: The GutHub actions does not work due to the following error

message='net::ERR_CONNECTION_REFUSED at http://localhost:8080/

=========================== logs ===========================

navigating to "http://localhost:8080/", waiting until "load"

============================================================

  name='Error
  stack='Error: net::ERR_CONNECTION_REFUSED at http://localhost:8080/

=========================== logs ===========================

navigating to "http://localhost:8080/", waiting until "load"

============================================================

at FrameSession._navigate (/tmp/playwright-java-1488943034847455992/package/lib/server/chromium/crPage.js:508:35)
at async Frame._gotoAction (/tmp/playwright-java-1488943034847455992/package/lib/server/frames.js:533:28)

---

This issue will have to be resolved but at the moment no solution has been found.
