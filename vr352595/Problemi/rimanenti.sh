#!/bin/sh

SCRIPTDIR=`dirname $0`

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su PLA031-1.008.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su PLA031-1.008.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -kbo -stP -limit300 -test

#### sos

echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su PLA031-1.008.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PLA031-1.008.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PLA031-1.008.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PLA031-1.008.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PLA031-1.008.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PLA031-1.008.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PLA031-1.008.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PLA031-1.008.p -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PLA031-1.008.p/\n"

####################################################################################################################
####################################################################################################################
####################################################################################################################

####################################################################################################################
####################################################################################################################
####################################################################################################################

##### Otter #####

echo "Al termine dello script è possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ001-3.p/\n"

mkdir $SCRIPTDIR/tptp.file/risultati/PUZ001-3.p

SCRIPTDIR=`dirname $0`

echo "Otter senza ordinamento (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -lex -usP -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -mul -usP -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -kbo -usP -limit300 -test

echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -kbo -stP -limit300 -test

###### sos

echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -sos -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -lex -usP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -mul -usP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -kbo -usP -sos -limit300 -test

echo "Otter con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -lex -stP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -mul -stP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -kbo -stP -sos -limit300 -test



####################################################################################################################

##### E #####

echo "E senza ordinamento (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -lex -usP -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -mul -usP -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -kbo -usP -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -kbo -stP -limit300 -test

#### sos

echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ001-3.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ001-3.p -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ001-3.p/\n"

####################################################################################################################
####################################################################################################################
####################################################################################################################

####################################################################################################################
####################################################################################################################
####################################################################################################################

##### Otter #####

echo "Al termine dello script è possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ003-1.p/\n"

mkdir $SCRIPTDIR/tptp.file/risultati/PUZ003-1.p

SCRIPTDIR=`dirname $0`

echo "Otter senza ordinamento (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -lex -usP -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -mul -usP -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -kbo -usP -limit300 -test

echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -kbo -stP -limit300 -test

###### sos

echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -sos -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -lex -usP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -mul -usP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -kbo -usP -sos -limit300 -test

echo "Otter con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -lex -stP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -mul -stP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -kbo -stP -sos -limit300 -test



####################################################################################################################

##### E #####

echo "E senza ordinamento (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -lex -usP -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -mul -usP -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -kbo -usP -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -kbo -stP -limit300 -test

#### sos

echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ003-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ003-1.p -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ003-1.p/\n"

####################################################################################################################
####################################################################################################################
####################################################################################################################

####################################################################################################################
####################################################################################################################
####################################################################################################################

##### Otter #####

echo "Al termine dello script è possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ014-1.p/\n"

mkdir $SCRIPTDIR/tptp.file/risultati/PUZ014-1.p

SCRIPTDIR=`dirname $0`

echo "Otter senza ordinamento (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -lex -usP -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -mul -usP -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -kbo -usP -limit300 -test

echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -kbo -stP -limit300 -test

###### sos

echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -sos -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -lex -usP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -mul -usP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -kbo -usP -sos -limit300 -test

echo "Otter con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -lex -stP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -mul -stP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -kbo -stP -sos -limit300 -test



####################################################################################################################

##### E #####

echo "E senza ordinamento (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -lex -usP -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -mul -usP -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -kbo -usP -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -kbo -stP -limit300 -test

#### sos

echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su PUZ014-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/PUZ014-1.p -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/PUZ014-1.p/\n"

####################################################################################################################
####################################################################################################################
####################################################################################################################



####################################################################################################################
####################################################################################################################
####################################################################################################################

##### Otter #####

echo "Al termine dello script è possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/RNG039-2.p/\n"

mkdir $SCRIPTDIR/tptp.file/risultati/RNG039-2.p

SCRIPTDIR=`dirname $0`

echo "Otter senza ordinamento (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -lex -usP -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -mul -usP -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -kbo -usP -limit300 -test

echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -kbo -stP -limit300 -test

###### sos

echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -sos -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -lex -usP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -mul -usP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -kbo -usP -sos -limit300 -test

echo "Otter con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -lex -stP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -mul -stP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -kbo -stP -sos -limit300 -test



####################################################################################################################

##### E #####

echo "E senza ordinamento (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -lex -usP -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -mul -usP -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -kbo -usP -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -kbo -stP -limit300 -test

#### sos

echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su RNG039-2.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su RNG039-2.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/RNG039-2.p -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/RNG039-2.p/\n"

####################################################################################################################
####################################################################################################################
####################################################################################################################

####################################################################################################################
####################################################################################################################
####################################################################################################################

##### Otter #####

echo "Al termine dello script è possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/SYN547-1.p/\n"

mkdir $SCRIPTDIR/tptp.file/risultati/SYN547-1.p

SCRIPTDIR=`dirname $0`

echo "Otter senza ordinamento (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -lex -usP -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -mul -usP -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -kbo -usP -limit300 -test

echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -kbo -stP -limit300 -test

###### sos

echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -sos -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -lex -usP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -mul -usP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -kbo -usP -sos -limit300 -test

echo "Otter con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -lex -stP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -mul -stP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -kbo -stP -sos -limit300 -test



####################################################################################################################

##### E #####

echo "E senza ordinamento (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -lex -usP -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -mul -usP -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -kbo -usP -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -kbo -stP -limit300 -test

#### sos

echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su SYN547-1.p"

#$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su SYN547-1.p"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/tptp.file/SYN547-1.p -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/tptp.file/risultati/SYN547-1.p/\n"

####################################################################################################################
####################################################################################################################
####################################################################################################################
