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

echo "==== Running coverity ===="

ncpus=$(cat /proc/cpuinfo|grep processor|wc -l)
export AUTOMAKE_JOBS=$ncpus

eval $pre_cmd

cov_build="cov_build"
rm -rf $cov_build

module load tools/cov

cov-build --dir $cov_build $build_cmd all
for item in ${ignore_list}; do
	cov-manage-emit --dir ${cov_build} --tu-pattern "file(${item})" delete ||:
done

cov-analyze --jobs $ncpus $COV_OPT --security --concurrency --dir $cov_build
cov-format-errors --dir $cov_build --emacs-style |& tee cov_${variant}.log

nerrors=$(cov-format-errors --dir $cov_build | awk '/Processing [0-9]+ errors?/ { print $2 }')
rc=$(($rc+$nerrors))

echo status $rc

exit $rc
