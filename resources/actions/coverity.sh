#!/bin/bash -eEl

pre_cmd=$1
build_cmd=$2
ignore_list=$3

topdir=$(git rev-parse --show-toplevel)
cd $topdir


if [ ! -d .git ]; then
	echo "Error: should be run from project root"
	exit 1
fi


ncpus=$(cat /proc/cpuinfo|grep processor|wc -l)
export AUTOMAKE_JOBS=$ncpus

echo "==== Running Pre-commands ===="

set +eE
/bin/bash -c "$pre_cmd"
rc=$?

if [ $rc -ne 0 ]; then
	echo pre-commands failed
	exit 1
fi

set -eE

cov_build="cov_build"
rm -rf $cov_build

module load tools/cov

echo "==== Running coverity ===="

cov-build --dir $cov_build $build_cmd all
for item in ${ignore_list}; do
	cov-manage-emit --dir ${cov_build} --tu-pattern "file(${item})" delete ||:
done

echo "==== Running anaysis ===="

cov-analyze --jobs $ncpus $COV_OPT --security --concurrency --dir $cov_build
cov-format-errors --dir $cov_build --emacs-style |& tee cov_${variant}.log

nerrors=$(cov-format-errors --dir $cov_build | awk '/Processing [0-9]+ errors?/ { print $2 }')
rc=$(($rc+$nerrors))

echo status $rc

exit $rc
