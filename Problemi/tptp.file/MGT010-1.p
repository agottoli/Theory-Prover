%--------------------------------------------------------------------------
% File     : MGT010-1 : TPTP v5.3.0. Released v2.4.0.
% Domain   : Management (Organisation Theory)
% Problem  : Large organization have higher reliability and accountability
% Version  : [PB+94] axioms.
% English  : Large organization have higher reliability and accountability
%            than small organizations (of the same class).

% Refs     : [PB+92] Peli et al. (1992), A Logical Approach to Formalizing
%          : [PB+94] Peli et al. (1994), A Logical Approach to Formalizing
%          : [Kam94] Kamps (1994), Email to G. Sutcliffe
% Source   : [TPTP]
% Names    :

% Status   : Unsatisfiable
% Rating   : 0.06 v5.3.0, 0.10 v5.2.0, 0.00 v2.4.0
% Syntax   : Number of clauses     :   19 (   0 non-Horn;  13 unit;  19 RR)
%            Number of atoms       :   60 (   0 equality)
%            Maximal clause size   :   12 (   3 average)
%            Number of predicates  :    8 (   0 propositional; 2-3 arity)
%            Number of functors    :   12 (  11 constant; 0-2 arity)
%            Number of variables   :   41 (   4 singleton)
%            Maximal term depth    :    2 (   1 average)
% SPC      : CNF_UNS_RFO_NEQ_HRN

% Comments : Created with tptp2X -f tptp -t clausify:otter MGT010+1.p
%--------------------------------------------------------------------------
cnf(mp3_1,axiom,
    ( ~ organization(A,B)
    | reproducibility(A,sk1(B,A),B) )).

cnf(a2_FOL_2,hypothesis,
    ( ~ organization(A,B)
    | ~ organization(C,D)
    | ~ reliability(A,E,B)
    | ~ reliability(C,F,D)
    | ~ accountability(A,G,B)
    | ~ accountability(C,H,D)
    | ~ reproducibility(A,I,B)
    | ~ reproducibility(C,J,D)
    | ~ greater(J,I)
    | greater(F,E) )).

cnf(a2_FOL_3,hypothesis,
    ( ~ organization(A,B)
    | ~ organization(C,D)
    | ~ reliability(A,E,B)
    | ~ reliability(C,F,D)
    | ~ accountability(A,G,B)
    | ~ accountability(C,H,D)
    | ~ reproducibility(A,I,B)
    | ~ reproducibility(C,J,D)
    | ~ greater(J,I)
    | greater(H,G) )).

cnf(a2_FOL_4,hypothesis,
    ( ~ organization(A,B)
    | ~ organization(C,D)
    | ~ reliability(A,E,B)
    | ~ reliability(C,F,D)
    | ~ accountability(A,G,B)
    | ~ accountability(C,H,D)
    | ~ reproducibility(A,I,B)
    | ~ reproducibility(C,J,D)
    | ~ greater(F,E)
    | ~ greater(H,G)
    | greater(J,I) )).

cnf(t9_FOL_5,hypothesis,
    ( ~ organization(A,B)
    | ~ organization(C,D)
    | ~ reorganization_free(A,B,B)
    | ~ reorganization_free(C,D,D)
    | ~ class(A,E,B)
    | ~ class(C,E,D)
    | ~ reproducibility(A,F,B)
    | ~ reproducibility(C,G,D)
    | ~ size(A,H,B)
    | ~ size(C,I,D)
    | ~ greater(I,H)
    | greater(G,F) )).

cnf(t10_FOL_6,negated_conjecture,
    ( organization(sk2,sk11) )).

cnf(t10_FOL_7,negated_conjecture,
    ( organization(sk3,sk12) )).

cnf(t10_FOL_8,negated_conjecture,
    ( reorganization_free(sk2,sk11,sk11) )).

cnf(t10_FOL_9,negated_conjecture,
    ( reorganization_free(sk3,sk12,sk12) )).

cnf(t10_FOL_10,negated_conjecture,
    ( class(sk2,sk4,sk11) )).

cnf(t10_FOL_11,negated_conjecture,
    ( class(sk3,sk4,sk12) )).

cnf(t10_FOL_12,negated_conjecture,
    ( reliability(sk2,sk5,sk11) )).

cnf(t10_FOL_13,negated_conjecture,
    ( reliability(sk3,sk6,sk12) )).

cnf(t10_FOL_14,negated_conjecture,
    ( accountability(sk2,sk7,sk11) )).

cnf(t10_FOL_15,negated_conjecture,
    ( accountability(sk3,sk8,sk12) )).

cnf(t10_FOL_16,negated_conjecture,
    ( size(sk2,sk9,sk11) )).

cnf(t10_FOL_17,negated_conjecture,
    ( size(sk3,sk10,sk12) )).

cnf(t10_FOL_18,negated_conjecture,
    ( greater(sk10,sk9) )).

cnf(t10_FOL_19,negated_conjecture,
    ( ~ greater(sk6,sk5)
    | ~ greater(sk8,sk7) )).

%--------------------------------------------------------------------------
