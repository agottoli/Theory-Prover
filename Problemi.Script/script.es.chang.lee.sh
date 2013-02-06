#!/bin/sh

SCRIPTDIR=`dirname $0`

echo "Al termine dello script è possibile visualizzare i risultati del test in $SCRIPTDIR/es.chang.lee/risultati/geo.txt/\n"
mkdir $SCRIPTDIR/es.chang.lee/risultati
mkdir $SCRIPTDIR/es.chang.lee/risultati/geo.txt


echo "Otter senza ordinamento (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -limit300 -test


echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -kbo -stP -limit300 -test



echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -sos -limit300 -test




echo "E senza ordinamento (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -E -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -E -kbo -stP -limit300 -test



echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su geo.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/geo.txt -E -sos -limit300 -test





mkdir $SCRIPTDIR/es.chang.lee/risultati/monkey-banana.txt

echo "Otter senza ordinamento (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -lex -usP -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -mul -usP -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -kbo -usP -limit300 -test

echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -kbo -stP -limit300 -test



echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -sos -limit300 -test

echo "Otter con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -lex -usP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -mul -usP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -kbo -usP -sos -limit300 -test

echo "Otter con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -lex -stP -sos -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -mul -stP -sos -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -kbo -stP -sos -limit300 -test









mkdir $SCRIPTDIR/es.chang.lee/risultati/group.txt


echo "Otter senza ordinamento (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -limit300 -test


echo "Otter con ordinamento lexicografico predefinito (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -lex -stP -limit300 -test

echo "Otter con ordinamento multiinsieme predefinito (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -mul -stP -limit300 -test

echo "Otter con ordinamento knuth-bendix predefinito (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -kbo -stP -limit300 -test



echo "Otter senza ordinamento con insieme di supporto (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -sos -limit300 -test




echo "E senza ordinamento (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -E -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -E -kbo -stP -limit300 -test



echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su group.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/group.txt -E -sos -limit300 -test






echo "E senza ordinamento (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -lex -usP -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -mul -usP -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -kbo -usP -limit300 -test

echo "E con ordinamento lexicografico predefinito (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -lex -stP -limit300 -test

echo "E con ordinamento multiinsieme predefinito (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -mul -stP -limit300 -test

echo "E con ordinamento knuth-bendix predefinito (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -kbo -stP -limit300 -test



echo "E senza ordinamento con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -sos -limit300 -test

echo "E con ordinamento lexicografico definito dall'utente con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -lex -usP -sos -limit300 -test

echo "E con ordinamento multiinsieme definito dall'utente con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -mul -usP -sos -limit300 -test

echo "E con ordinamento knuth-bendix definito dall'utente con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -kbo -usP -sos -limit300 -test

echo "E con ordinamento lexicografico predefinito con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -lex -stP -sos -limit300 -test

echo "E con ordinamento multiinsieme predefinito con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -mul -stP -sos -limit300 -test

echo "E con ordinamento knuth-bendix predefinito con insieme di supporto (limite 5 minuti) su monkey-banana.txt"

$SCRIPTDIR/../vr352595.sh $SCRIPTDIR/es.chang.lee/monkey-banana.txt -E -kbo -stP -sos -limit300 -test
 
echo "E' possibile visualizzare i risultati del test in $SCRIPTDIR/es.chang.lee/risultati/monkey-banana.txt/\n"





