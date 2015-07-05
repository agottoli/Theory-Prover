#!/bin/sh

SCRIPTDIR=`dirname $0`

java -cp $SCRIPTDIR/../../build/classes:$SCRIPTDIR/../../build/test/classes thProver.test.AllTheFactorsTest $*
