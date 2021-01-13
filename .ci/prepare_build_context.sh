#!/bin/sh

# Prepare Apple mDNS responder for Docker build context
cd ${WORKSPACE}/
cp -v /auto/mtrswgwork/nmos-cpp_deps/mDNSResponder-878.200.35.tar.gz ./

if [ "$RIVERMAX_VERSION" == "" ]; then
    echo "Error: env variable RIVERMAX_VERSION is not defined or empty"
fi
if [ "${RIVERMAX_APPS_VERSION}" == "" ]; then
    echo "Error: env variable RIVERMAX_APPS_VERSION is not defined or empty"
fi

# Prepare Rivermax .deb package for Docker build context
ARCH=$(uname -m)
if [ "$ARCH" == "x86_64" ]; then
    cp -v /auto/sw/release/sw_acceleration/rivermax/$RIVERMAX_VERSION/Ubuntu.18.04/deb-dist/x86_64/rivermax_${RIVERMAX_APPS_VERSION}_amd64.deb ./
    # cp -v /auto/mtrswgwork/nmos-cpp_deps/nv-tensorrt-repo-ubuntu1804-cuda11.1-trt7.2.1.6-ga-20201007_1-1_amd64.deb ./
else
    cp -v /auto/sw/release/sw_acceleration/rivermax/$RIVERMAX_VERSION/Ubuntu.18.04/deb-dist/aarch64/rivermax_${RIVERMAX_APPS_VERSION}_arm64.deb ./
    # cp -v /auto/mtrswgwork/nmos-cpp_deps/nv-tensorrt-repo-ubuntu1804-cuda11.1-trt7.2.1.6-ga-20201007_1-1_arm64.deb ./
fi
