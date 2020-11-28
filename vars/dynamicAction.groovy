#!/usr/bin/env groovy

@NonCPS
def call(actionName, preCmd, buildCmd) {

    def actionScript = libraryResource 'actions/coverity.sh'

    println("==>DynamicAction ($actionName, $preCmd, $buildCmd) == " + actionScript)

    return;
}
