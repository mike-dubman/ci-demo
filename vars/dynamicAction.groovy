#!/usr/bin/env groovy

def call(actionName, preCmd, buildCmd) {

    def actionScript = libraryResource 'actions/coverity.sh'

    println("==>DynamicAction ($actionName, $preCmd, $buildCmd) == " + actionScript)

    return;
}
