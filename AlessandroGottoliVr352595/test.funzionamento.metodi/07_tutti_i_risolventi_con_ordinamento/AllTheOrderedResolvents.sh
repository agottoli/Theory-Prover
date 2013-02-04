#!/bin/sh

SCRIPTDIR=`dirname $0`

java -cp $SCRIPTDIR/../../Project/dist/DimostratoreTeoremi-RA2012-2013.jar:$SCRIPTDIR/../../Project/build/test/classes thProver.test.AllTheOrderedResolventsTest $*
