digraph {
	nodesep="1.5"; ranksep=2;
	node [shape=plaintext];
	edge [color=gray];
	"green(c)" -> "[]" [labelfontcolor=black,labelfontsize="12",headlabel="ris. ord.\n{ }",labeldistance="6"];
	"green(b)" -> "green(c)" [labelfontcolor=black,labelfontsize="12",headlabel="sempl. claus.\n{ }",labeldistance="6"];
	"green(a)" -> "green(b)" [labelfontcolor=black,labelfontsize="12",headlabel="sempl. claus.\n{ }",labeldistance="6"];
	"~green(a) | green(b)" -> "green(b)" ;
	"~green(X_4) | green(Y_4) | ~on(X_4,Y_4)" -> "~green(a) | green(b)" [labelfontcolor=black,labelfontsize="12",headlabel="ris. ord.\n{ X_4 <- a, Y_4 <- b }",labeldistance="6"];
	"on(a,b)" -> "~green(a) | green(b)" ;
	"~green(b) | green(c)" -> "green(c)" ;
	"~green(X_4) | green(Y_4) | ~on(X_4,Y_4)" -> "~green(b) | green(c)" [labelfontcolor=black,labelfontsize="12",headlabel="ris. ord.\n{ X_4 <- b, Y_4 <- c }",labeldistance="6"];
	"on(b,c)" -> "~green(b) | green(c)" ;
	"~green(c)" -> "[]" ;
}

