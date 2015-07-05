# Theory-Prover
 =========================================

Implementazione in Java di un dimostratore di teoremi per la logica del primo ordine senza simboli interpretati per risoluzione ordinata basato su ciclo della clausola data à la Otter e à la E.

Sono presenti diversi tipi di ordinamento e di risoluzione che l'utente può scegliere.

La dimostrazione viene restituita come file 
*dot* oppure si può decidere di esportarla direttamente in *jpg*, *ps* o *pdf* (se graphviz è installato sul pc)


* Studente: Alessandro Gottoli - VR352595
* Corso: Ragionamento Automatico
* Anno Accademico: 2012-2013
* Docente: Prof. Maria Paola Bonacina

## Usage (consigliato)

    vr352595.sh -gui

## Usage completo: 

    vr352595.sh [-gui | -i | <filename>] [-sos] [(-lex | -mul | -kbo) [-usP]] [-E] [-limit<secs>] [-vAll]

        -gui    avvia l'interfaccia grafica
        -i      avvia la modalità a riga di comando in cui inserire le clausole da standard input
        <filename>   è il file da cui leggere le clausole
        -sos    abilità la strategia dell'insieme di supporto
   
     di default non verrà utilizzato nessun ordinamento, ma si possono attivare con i seguenti:
        -lex    usa l'ordinamento lessicografico 
        -mul    usa l'ordinamento multiinsieme 
        -kbo    usa l'ordinamento knuth-bendix
     se viene attivato un ordinamento, di default uso la precedenza e i pesi definiti da me per fare ordinamento
     ma se l'utente vuole usare la sua data in input puo farlo con:
        -usP    usa le precedenze e i pesi dati in input dall'utente

    di default usa il ciclo della clausola data à la Otter, ma è possibile cambiarlo con:
        -E      usa la versione à la E

    di default non c'è limite al tempo di comutazione, ma si può impostare con:
        -limit<secs>    specifica il tempo di timeout (in secondi) (es. -limit300 imposta un limite di 5 minuti)

    sono disponibile delle ulteriori opzioni solo per test:
        -changlee    usa la sussunzione trovata sul libro di Chang-Lee a pag. 95
        -vAll        usa una versione sperimentale in cui calcola tutti i risolventi.

> # ENJOY!!!!

