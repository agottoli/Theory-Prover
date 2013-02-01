%------------------------------------------------------------------------------
% File     : PLA031-1.008 : TPTP v5.4.0. Released v3.5.0.
% Domain   : Planning
% Problem  : Driver's log k=08
% Version  : Especial.
% English  : A planning domain that involves driving trucks around
%            delivering packages between locations. The complication is
%            that the trucks require drivers who must walk between trucks
%            in order to drive them. The paths for walking and the roads
%            for driving form different maps on the locations.
%            Instances were semi-automatically translated from the basic
%            Strips instances used in the 2002 Planning Competition.

% Refs     : [LF03] Long & Fox (2003), The 3rd International Planning Compe
%          : [NV07a] Navarro (2007), Email to Geoff Sutcliffe
%          : [NV07b] Navarro & Voronkov (2007), Encoding Problems and Reaso
% Source   : [NV07a]
% Names    : driverlog08 [NV07a]

% Status   : Unsatisfiable
% Rating   : 1.00 v5.2.0, 0.67 v5.0.0, 1.00 v3.5.0
% Syntax   : Number of clauses     :  192 (   0 non-Horn; 120 unit; 192 RR)
%            Number of atoms       :  294 (   0 equality)
%            Maximal clause size   :    4 (   2 average)
%            Number of predicates  :    4 (   0 propositional; 2-13 arity)
%            Number of functors    :   10 (  10 constant; 0-0 arity)
%            Number of variables   :  880 (   1 singleton)
%            Maximal term depth    :    1 (   1 average)
% SPC      : CNF_UNS_EPR

% Comments : Only instances 1-4 are within reach of current provers. (2007)
%          : Translated from [LF03] using [NV07b]
%------------------------------------------------------------------------------
cnf(load1,axiom,
    ( ~ s(L,O2,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | s(truck1,O2,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load2,axiom,
    ( s(L,O2,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | ~ s(truck1,O2,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load3,axiom,
    ( ~ s(L,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | s(truck2,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load4,axiom,
    ( s(L,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | ~ s(truck2,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load5,axiom,
    ( ~ s(L,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | s(truck3,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load6,axiom,
    ( s(L,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | ~ s(truck3,O2,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load7,axiom,
    ( ~ s(O1,L,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | s(O1,truck1,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load8,axiom,
    ( s(O1,L,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | ~ s(O1,truck1,O3,O4,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load9,axiom,
    ( ~ s(O1,L,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | s(O1,truck2,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load10,axiom,
    ( s(O1,L,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | ~ s(O1,truck2,O3,O4,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load11,axiom,
    ( ~ s(O1,L,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | s(O1,truck3,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load12,axiom,
    ( s(O1,L,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | ~ s(O1,truck3,O3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load13,axiom,
    ( ~ s(O1,O2,L,O4,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | s(O1,O2,truck1,O4,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load14,axiom,
    ( s(O1,O2,L,O4,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | ~ s(O1,O2,truck1,O4,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load15,axiom,
    ( ~ s(O1,O2,L,O4,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | s(O1,O2,truck2,O4,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load16,axiom,
    ( s(O1,O2,L,O4,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | ~ s(O1,O2,truck2,O4,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load17,axiom,
    ( ~ s(O1,O2,L,O4,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | s(O1,O2,truck3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load18,axiom,
    ( s(O1,O2,L,O4,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | ~ s(O1,O2,truck3,O4,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load19,axiom,
    ( ~ s(O1,O2,O3,L,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | s(O1,O2,O3,truck1,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load20,axiom,
    ( s(O1,O2,O3,L,O5,O6,O7,D1,D2,D3,L,T2,T3)
    | ~ s(O1,O2,O3,truck1,O5,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load21,axiom,
    ( ~ s(O1,O2,O3,L,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | s(O1,O2,O3,truck2,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load22,axiom,
    ( s(O1,O2,O3,L,O5,O6,O7,D1,D2,D3,T1,L,T3)
    | ~ s(O1,O2,O3,truck2,O5,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load23,axiom,
    ( ~ s(O1,O2,O3,L,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | s(O1,O2,O3,truck3,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load24,axiom,
    ( s(O1,O2,O3,L,O5,O6,O7,D1,D2,D3,T1,T2,L)
    | ~ s(O1,O2,O3,truck3,O5,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load25,axiom,
    ( ~ s(O1,O2,O3,O4,L,O6,O7,D1,D2,D3,L,T2,T3)
    | s(O1,O2,O3,O4,truck1,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load26,axiom,
    ( s(O1,O2,O3,O4,L,O6,O7,D1,D2,D3,L,T2,T3)
    | ~ s(O1,O2,O3,O4,truck1,O6,O7,D1,D2,D3,L,T2,T3) )).

cnf(load27,axiom,
    ( ~ s(O1,O2,O3,O4,L,O6,O7,D1,D2,D3,T1,L,T3)
    | s(O1,O2,O3,O4,truck2,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load28,axiom,
    ( s(O1,O2,O3,O4,L,O6,O7,D1,D2,D3,T1,L,T3)
    | ~ s(O1,O2,O3,O4,truck2,O6,O7,D1,D2,D3,T1,L,T3) )).

cnf(load29,axiom,
    ( ~ s(O1,O2,O3,O4,L,O6,O7,D1,D2,D3,T1,T2,L)
    | s(O1,O2,O3,O4,truck3,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load30,axiom,
    ( s(O1,O2,O3,O4,L,O6,O7,D1,D2,D3,T1,T2,L)
    | ~ s(O1,O2,O3,O4,truck3,O6,O7,D1,D2,D3,T1,T2,L) )).

cnf(load31,axiom,
    ( ~ s(O1,O2,O3,O4,O5,L,O7,D1,D2,D3,L,T2,T3)
    | s(O1,O2,O3,O4,O5,truck1,O7,D1,D2,D3,L,T2,T3) )).

cnf(load32,axiom,
    ( s(O1,O2,O3,O4,O5,L,O7,D1,D2,D3,L,T2,T3)
    | ~ s(O1,O2,O3,O4,O5,truck1,O7,D1,D2,D3,L,T2,T3) )).

cnf(load33,axiom,
    ( ~ s(O1,O2,O3,O4,O5,L,O7,D1,D2,D3,T1,L,T3)
    | s(O1,O2,O3,O4,O5,truck2,O7,D1,D2,D3,T1,L,T3) )).

cnf(load34,axiom,
    ( s(O1,O2,O3,O4,O5,L,O7,D1,D2,D3,T1,L,T3)
    | ~ s(O1,O2,O3,O4,O5,truck2,O7,D1,D2,D3,T1,L,T3) )).

cnf(load35,axiom,
    ( ~ s(O1,O2,O3,O4,O5,L,O7,D1,D2,D3,T1,T2,L)
    | s(O1,O2,O3,O4,O5,truck3,O7,D1,D2,D3,T1,T2,L) )).

cnf(load36,axiom,
    ( s(O1,O2,O3,O4,O5,L,O7,D1,D2,D3,T1,T2,L)
    | ~ s(O1,O2,O3,O4,O5,truck3,O7,D1,D2,D3,T1,T2,L) )).

cnf(load37,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,L,D1,D2,D3,L,T2,T3)
    | s(O1,O2,O3,O4,O5,O6,truck1,D1,D2,D3,L,T2,T3) )).

cnf(load38,axiom,
    ( s(O1,O2,O3,O4,O5,O6,L,D1,D2,D3,L,T2,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,truck1,D1,D2,D3,L,T2,T3) )).

cnf(load39,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,L,D1,D2,D3,T1,L,T3)
    | s(O1,O2,O3,O4,O5,O6,truck2,D1,D2,D3,T1,L,T3) )).

cnf(load40,axiom,
    ( s(O1,O2,O3,O4,O5,O6,L,D1,D2,D3,T1,L,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,truck2,D1,D2,D3,T1,L,T3) )).

cnf(load41,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,L,D1,D2,D3,T1,T2,L)
    | s(O1,O2,O3,O4,O5,O6,truck3,D1,D2,D3,T1,T2,L) )).

cnf(load42,axiom,
    ( s(O1,O2,O3,O4,O5,O6,L,D1,D2,D3,T1,T2,L)
    | ~ s(O1,O2,O3,O4,O5,O6,truck3,D1,D2,D3,T1,T2,L) )).

cnf(board1,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,L,D2,D3,L,T2,T3)
    | ~ neq(D2,truck1)
    | ~ neq(D3,truck1)
    | s(O1,O2,O3,O4,O5,O6,O7,truck1,D2,D3,L,T2,T3) )).

cnf(board2,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,L,D2,D3,L,T2,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,truck1,D2,D3,L,T2,T3) )).

cnf(board3,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,L,D2,D3,T1,L,T3)
    | ~ neq(D2,truck2)
    | ~ neq(D3,truck2)
    | s(O1,O2,O3,O4,O5,O6,O7,truck2,D2,D3,T1,L,T3) )).

cnf(board4,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,L,D2,D3,T1,L,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,truck2,D2,D3,T1,L,T3) )).

cnf(board5,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,L,D2,D3,T1,T2,L)
    | ~ neq(D2,truck3)
    | ~ neq(D3,truck3)
    | s(O1,O2,O3,O4,O5,O6,O7,truck3,D2,D3,T1,T2,L) )).

cnf(board6,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,L,D2,D3,T1,T2,L)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,truck3,D2,D3,T1,T2,L) )).

cnf(board7,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,L,D3,L,T2,T3)
    | ~ neq(D1,truck1)
    | ~ neq(D3,truck1)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,truck1,D3,L,T2,T3) )).

cnf(board8,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,D1,L,D3,L,T2,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,D1,truck1,D3,L,T2,T3) )).

cnf(board9,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,L,D3,T1,L,T3)
    | ~ neq(D1,truck2)
    | ~ neq(D3,truck2)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,truck2,D3,T1,L,T3) )).

cnf(board10,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,D1,L,D3,T1,L,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,D1,truck2,D3,T1,L,T3) )).

cnf(board11,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,L,D3,T1,T2,L)
    | ~ neq(D1,truck3)
    | ~ neq(D3,truck3)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,truck3,D3,T1,T2,L) )).

cnf(board12,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,D1,L,D3,T1,T2,L)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,D1,truck3,D3,T1,T2,L) )).

cnf(board13,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,L,L,T2,T3)
    | ~ neq(D1,truck1)
    | ~ neq(D2,truck1)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck1,L,T2,T3) )).

cnf(board14,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,D1,D2,L,L,T2,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck1,L,T2,T3) )).

cnf(board15,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,L,T1,L,T3)
    | ~ neq(D1,truck2)
    | ~ neq(D2,truck2)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck2,T1,L,T3) )).

cnf(board16,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,D1,D2,L,T1,L,T3)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck2,T1,L,T3) )).

cnf(board17,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,L,T1,T2,L)
    | ~ neq(D1,truck3)
    | ~ neq(D2,truck3)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck3,T1,T2,L) )).

cnf(board18,axiom,
    ( s(O1,O2,O3,O4,O5,O6,O7,D1,D2,L,T1,T2,L)
    | ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck3,T1,T2,L) )).

cnf(drive1,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,truck1,D2,D3,S,T2,T3)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,truck1,D2,D3,D,T2,T3) )).

cnf(drive2,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,truck2,D2,D3,T1,S,T3)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,truck2,D2,D3,T1,D,T3) )).

cnf(drive3,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,truck3,D2,D3,T1,T2,S)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,truck3,D2,D3,T1,T2,D) )).

cnf(drive4,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,truck1,D3,S,T2,T3)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,truck1,D3,D,T2,T3) )).

cnf(drive5,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,truck2,D3,T1,S,T3)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,truck2,D3,T1,D,T3) )).

cnf(drive6,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,truck3,D3,T1,T2,S)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,truck3,D3,T1,T2,D) )).

cnf(drive7,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck1,S,T2,T3)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck1,D,T2,T3) )).

cnf(drive8,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck2,T1,S,T3)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck2,T1,D,T3) )).

cnf(drive9,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck3,T1,T2,S)
    | ~ link(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D2,truck3,T1,T2,D) )).

cnf(walk1,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,S,D2,D3,T1,T2,T3)
    | ~ path(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D,D2,D3,T1,T2,T3) )).

cnf(walk2,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,S,D3,T1,T2,T3)
    | ~ path(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D,D3,T1,T2,T3) )).

cnf(walk3,axiom,
    ( ~ s(O1,O2,O3,O4,O5,O6,O7,D1,D2,S,T1,T2,T3)
    | ~ path(S,D)
    | s(O1,O2,O3,O4,O5,O6,O7,D1,D2,D,T1,T2,T3) )).

cnf(neq1,axiom,
    ( ~ neq(truck1,truck1) )).

cnf(neq2,axiom,
    ( neq(truck1,truck2) )).

cnf(neq3,axiom,
    ( neq(truck1,truck3) )).

cnf(neq4,axiom,
    ( neq(truck1,s0) )).

cnf(neq5,axiom,
    ( neq(truck1,s1) )).

cnf(neq6,axiom,
    ( neq(truck1,s2) )).

cnf(neq7,axiom,
    ( neq(truck1,p0_1) )).

cnf(neq8,axiom,
    ( neq(truck1,p0_2) )).

cnf(neq9,axiom,
    ( neq(truck1,p1_2) )).

cnf(neq10,axiom,
    ( neq(truck1,p2_0) )).

cnf(neq11,axiom,
    ( neq(truck2,truck1) )).

cnf(neq12,axiom,
    ( ~ neq(truck2,truck2) )).

cnf(neq13,axiom,
    ( neq(truck2,truck3) )).

cnf(neq14,axiom,
    ( neq(truck2,s0) )).

cnf(neq15,axiom,
    ( neq(truck2,s1) )).

cnf(neq16,axiom,
    ( neq(truck2,s2) )).

cnf(neq17,axiom,
    ( neq(truck2,p0_1) )).

cnf(neq18,axiom,
    ( neq(truck2,p0_2) )).

cnf(neq19,axiom,
    ( neq(truck2,p1_2) )).

cnf(neq20,axiom,
    ( neq(truck2,p2_0) )).

cnf(neq21,axiom,
    ( neq(truck3,truck1) )).

cnf(neq22,axiom,
    ( neq(truck3,truck2) )).

cnf(neq23,axiom,
    ( ~ neq(truck3,truck3) )).

cnf(neq24,axiom,
    ( neq(truck3,s0) )).

cnf(neq25,axiom,
    ( neq(truck3,s1) )).

cnf(neq26,axiom,
    ( neq(truck3,s2) )).

cnf(neq27,axiom,
    ( neq(truck3,p0_1) )).

cnf(neq28,axiom,
    ( neq(truck3,p0_2) )).

cnf(neq29,axiom,
    ( neq(truck3,p1_2) )).

cnf(neq30,axiom,
    ( neq(truck3,p2_0) )).

cnf(neq31,axiom,
    ( neq(s0,truck1) )).

cnf(neq32,axiom,
    ( neq(s0,truck2) )).

cnf(neq33,axiom,
    ( neq(s0,truck3) )).

cnf(neq34,axiom,
    ( ~ neq(s0,s0) )).

cnf(neq35,axiom,
    ( neq(s0,s1) )).

cnf(neq36,axiom,
    ( neq(s0,s2) )).

cnf(neq37,axiom,
    ( neq(s0,p0_1) )).

cnf(neq38,axiom,
    ( neq(s0,p0_2) )).

cnf(neq39,axiom,
    ( neq(s0,p1_2) )).

cnf(neq40,axiom,
    ( neq(s0,p2_0) )).

cnf(neq41,axiom,
    ( neq(s1,truck1) )).

cnf(neq42,axiom,
    ( neq(s1,truck2) )).

cnf(neq43,axiom,
    ( neq(s1,truck3) )).

cnf(neq44,axiom,
    ( neq(s1,s0) )).

cnf(neq45,axiom,
    ( ~ neq(s1,s1) )).

cnf(neq46,axiom,
    ( neq(s1,s2) )).

cnf(neq47,axiom,
    ( neq(s1,p0_1) )).

cnf(neq48,axiom,
    ( neq(s1,p0_2) )).

cnf(neq49,axiom,
    ( neq(s1,p1_2) )).

cnf(neq50,axiom,
    ( neq(s1,p2_0) )).

cnf(neq51,axiom,
    ( neq(s2,truck1) )).

cnf(neq52,axiom,
    ( neq(s2,truck2) )).

cnf(neq53,axiom,
    ( neq(s2,truck3) )).

cnf(neq54,axiom,
    ( neq(s2,s0) )).

cnf(neq55,axiom,
    ( neq(s2,s1) )).

cnf(neq56,axiom,
    ( ~ neq(s2,s2) )).

cnf(neq57,axiom,
    ( neq(s2,p0_1) )).

cnf(neq58,axiom,
    ( neq(s2,p0_2) )).

cnf(neq59,axiom,
    ( neq(s2,p1_2) )).

cnf(neq60,axiom,
    ( neq(s2,p2_0) )).

cnf(neq61,axiom,
    ( neq(p0_1,truck1) )).

cnf(neq62,axiom,
    ( neq(p0_1,truck2) )).

cnf(neq63,axiom,
    ( neq(p0_1,truck3) )).

cnf(neq64,axiom,
    ( neq(p0_1,s0) )).

cnf(neq65,axiom,
    ( neq(p0_1,s1) )).

cnf(neq66,axiom,
    ( neq(p0_1,s2) )).

cnf(neq67,axiom,
    ( ~ neq(p0_1,p0_1) )).

cnf(neq68,axiom,
    ( neq(p0_1,p0_2) )).

cnf(neq69,axiom,
    ( neq(p0_1,p1_2) )).

cnf(neq70,axiom,
    ( neq(p0_1,p2_0) )).

cnf(neq71,axiom,
    ( neq(p0_2,truck1) )).

cnf(neq72,axiom,
    ( neq(p0_2,truck2) )).

cnf(neq73,axiom,
    ( neq(p0_2,truck3) )).

cnf(neq74,axiom,
    ( neq(p0_2,s0) )).

cnf(neq75,axiom,
    ( neq(p0_2,s1) )).

cnf(neq76,axiom,
    ( neq(p0_2,s2) )).

cnf(neq77,axiom,
    ( neq(p0_2,p0_1) )).

cnf(neq78,axiom,
    ( ~ neq(p0_2,p0_2) )).

cnf(neq79,axiom,
    ( neq(p0_2,p1_2) )).

cnf(neq80,axiom,
    ( neq(p0_2,p2_0) )).

cnf(neq81,axiom,
    ( neq(p1_2,truck1) )).

cnf(neq82,axiom,
    ( neq(p1_2,truck2) )).

cnf(neq83,axiom,
    ( neq(p1_2,truck3) )).

cnf(neq84,axiom,
    ( neq(p1_2,s0) )).

cnf(neq85,axiom,
    ( neq(p1_2,s1) )).

cnf(neq86,axiom,
    ( neq(p1_2,s2) )).

cnf(neq87,axiom,
    ( neq(p1_2,p0_1) )).

cnf(neq88,axiom,
    ( neq(p1_2,p0_2) )).

cnf(neq89,axiom,
    ( ~ neq(p1_2,p1_2) )).

cnf(neq90,axiom,
    ( neq(p1_2,p2_0) )).

cnf(neq91,axiom,
    ( neq(p2_0,truck1) )).

cnf(neq92,axiom,
    ( neq(p2_0,truck2) )).

cnf(neq93,axiom,
    ( neq(p2_0,truck3) )).

cnf(neq94,axiom,
    ( neq(p2_0,s0) )).

cnf(neq95,axiom,
    ( neq(p2_0,s1) )).

cnf(neq96,axiom,
    ( neq(p2_0,s2) )).

cnf(neq97,axiom,
    ( neq(p2_0,p0_1) )).

cnf(neq98,axiom,
    ( neq(p2_0,p0_2) )).

cnf(neq99,axiom,
    ( neq(p2_0,p1_2) )).

cnf(neq100,axiom,
    ( ~ neq(p2_0,p2_0) )).

cnf(map1,axiom,
    ( path(s0,p0_1) )).

cnf(map2,axiom,
    ( path(p0_1,s0) )).

cnf(map3,axiom,
    ( path(s1,p0_1) )).

cnf(map4,axiom,
    ( path(p0_1,s1) )).

cnf(map5,axiom,
    ( path(s0,p0_2) )).

cnf(map6,axiom,
    ( path(p0_2,s0) )).

cnf(map7,axiom,
    ( path(s2,p0_2) )).

cnf(map8,axiom,
    ( path(p0_2,s2) )).

cnf(map9,axiom,
    ( path(s1,p1_2) )).

cnf(map10,axiom,
    ( path(p1_2,s1) )).

cnf(map11,axiom,
    ( path(s2,p1_2) )).

cnf(map12,axiom,
    ( path(p1_2,s2) )).

cnf(map13,axiom,
    ( link(s0,s1) )).

cnf(map14,axiom,
    ( link(s1,s0) )).

cnf(map15,axiom,
    ( link(s0,s2) )).

cnf(map16,axiom,
    ( link(s2,s0) )).

cnf(map17,axiom,
    ( link(s1,s2) )).

cnf(map18,axiom,
    ( link(s2,s1) )).

cnf(init,axiom,
    ( s(s1,s0,s0,s1,s1,s0,s0,s1,s0,s2,s1,s0,s1) )).

cnf(goal,negated_conjecture,
    ( ~ s(s2,s1,s0,s2,s0,s1,s0,s0,s1,s2,X11,s2,s2) )).

%------------------------------------------------------------------------------
