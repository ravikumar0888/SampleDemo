## TestNG Reports

Link for [TestNG reports](https://scttspstests.gitlab-pages.kazan.atosworldline.com/sctt-sps-tests-automated/emailable-report.html) 


## Skipping jobs

If your commit message contains `[ci skip]` or `[skip ci]`, using any
capitalization, the commit will be created but the pipeline will be skipped.
Alternatively, one can pass the `ci.skip` [Git push option](https://git-scm.com/docs/git-push#Documentation/git-push.txt--oltoptiongt) if
using Git 2.10 or newer:
git push --push-option=ci.skip    # using git 2.10+
git push -o ci.skip               # using git 2.18+