#!/usr/bin/env groovy

def call(args) {

    library(identifier: 'ngci@ci_version-3.1',
            retriever: modernSCM([$class: 'GitSCMSource', 
            remote: 'http://l-gerrit.mtl.labs.mlnx:8080/DevOps/Jenkins/ci_framework']))

    println("==>ngci(" + args + ")")

    if (args.size() < 1) {
        println("fatal: DynamicAction() expects at least 1 parameter")
        sh("false")
    }
    def actionName = args[0]
    def callMe = actionName + "("
    
    def params = []
    if (args.size() > 1) {
        for (int i=1; i<args.size(); i++) {
            params.add(args[i])
            println("xxxx args[${i}]=" + args[i] + " class=" + args[i].getClass())
            if (i!=4) {
                callMe += args[i]
            } else {
                callMe += "'" + args[i] + "'"
            }
            if (i<args.size()-1) {
                callMe += ","
            }
        }
        callMe += ")"
    }

    
    println("Calling ${callMe}")
    //"$actionName"(params)
    evaluate(callMe)

    return;
}
