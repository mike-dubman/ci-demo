#!/usr/bin/env groovy

@NonCPS
def call(List args) {

    println("==>DynamicAction(" + args + ")")

    if (args.size() < 1) {
        println("fatal: DynamicAction() expects at least 1 parameter")
        sh("false")
    }
    def actionName = args[0]
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = new File (env.WORKSPACE + "/cidemo_" + actionName)

    println("action file: " + toFile.getAbsolutePath())
    println("xxxxx write " + cmd)
    writeFile(file: toFile.getAbsolutePath(), text: actionScript)
    sh("chmod +x " + toFile.getAbsolutePath())
    println("xxxxx passed write " + cmd)

    def cmd = toFile.getAbsolutePath()
    if (args.size() > 1) {
        cmd += " " + args.subList(1,args.size()).collect{ "'" + it + "'"}.join(" ")
    }
    println("xxxxx Running " + cmd)
    sh(cmd)
    println("xxxxx ${actionName} -- call done")
    return;
}
