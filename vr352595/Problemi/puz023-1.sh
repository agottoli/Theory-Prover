#!/bin/sh

SCRIPTDIR=`dirname $0`

####################################################################################################################
####################################################################################################################
####################################################################################################################

##### Otter #####

echo "Al termine dello script è possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ023-1.p/\n"

mkdir $SCRIPTDIR/tptp.file/risultati/PUZ023-1.p

SCRIPTDIR=`dirname $0`

echo "Otter senza ordinamento (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -lex -usP -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -mul -usP -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -kbo -usP -limit300 -test

echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -kbo -stP -limit300 -test

###### sos

echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -sos -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -lex -usP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -mul -usP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -kbo -usP -sos -limit300 -test

echo "Otter con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -lex -stP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -mul -stP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -kbo -stP -sos -limit300 -test



####################################################################################################################

##### E #####

echo "E senza ordinamento (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -lex -usP -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -mul -usP -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -kbo -usP -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -kbo -stP -limit300 -test

#### sos

echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ023-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ023-1.p -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ023-1.p/\n"

####################################################################################################################
####################################################################################################################
####################################################################################################################
