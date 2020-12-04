#!/usr/bin/env groovy

def call(List args) {

    library(identifier: 'ngci@ci_version-3.1',
            retriever: modernSCM([$class: 'GitSCMSource', 
            remote: 'http://l-gerrit.mtl.labs.mlnx:8080/DevOps/Jenkins/ci_framework']))

    println("==>ngci(" + args + ")")

    if (args.size() < 1) {
        println("fatal: DynamicAction() expects at least 1 parameter")
        sh("false")
    }
    def actionName = args[0]

    def cmd = ""
    if (args.size() > 1) {
        cmd += " " + args.subList(1,args.size()).collect{ "'" + it + "'"}.join(" ")
    }
    println("Running " + cmd)
    "$actionName"(cmd)

    return;
}
