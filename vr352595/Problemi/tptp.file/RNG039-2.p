%--------------------------------------------------------------------------
% File     : RNG039-2 : TPTP v5.3.0. Released v1.0.0.
% Domain   : Ring Theory
% Problem  : Ring property 2
% Version  : [Wos65] axioms : Reduced > Incomplete.
% English  :

% Refs     : [Wos65] Wos (1965), Unpublished Note
%          : [WM76]  Wilson & Minker (1976), Resolution, Refinements, and S
% Source   : [SPRFN]
% Names    : Problem 28 [Wos65]
%          : wos28 [WM76]

% Status   : Unsatisfiable
% Rating   : 0.11 v5.3.0, 0.15 v5.2.0, 0.00 v5.1.0, 0.06 v5.0.0, 0.07 v4.0.1, 0.00 v2.7.0, 0.12 v2.6.0, 0.43 v2.5.0, 0.14 v2.4.0, 0.00 v2.3.0, 0.14 v2.2.1, 0.44 v2.1.0, 0.29 v2.0.0
% Syntax   : Number of clauses     :   74 (   0 non-Horn;  50 unit;  49 RR)
%            Number of atoms       :  129 (   0 equality)
%            Maximal clause size   :    5 (   2 average)
%            Number of predicates  :    3 (   0 propositional; 2-3 arity)
%            Number of functors    :    7 (   5 constant; 0-2 arity)
%            Number of variables   :  139 (   3 singleton)
%            Maximal term depth    :    2 (   1 average)
% SPC      : CNF_UNS_RFO_NEQ_HRN

% Comments : There is no additive inverse in this problem.
%--------------------------------------------------------------------------
%----Include ring theory axioms
%include('Axioms/RNG001-0.ax').
%--------------------------------------------------------------------------
%input_clause(additive_inverse_substitution,axiom,
%    [--equalish(X,Y),
%     ++equalish(additive_inverse(X),additive_inverse(Y))]).

cnf(add_substitution1,axiom,
    ( ~ equalish(X,Y)
    | equalish(add(X,W),add(Y,W)) )).

cnf(add_substitution2,axiom,
    ( ~ equalish(X,Y)
    | equalish(add(W,X),add(W,Y)) )).

cnf(sum_substitution1,axiom,
    ( ~ equalish(X,Y)
    | ~ sum(X,W,Z)
    | sum(Y,W,Z) )).

cnf(sum_substitution2,axiom,
    ( ~ equalish(X,Y)
    | ~ sum(W,X,Z)
    | sum(W,Y,Z) )).

cnf(sum_substitution3,axiom,
    ( ~ equalish(X,Y)
    | ~ sum(W,Z,X)
    | sum(W,Z,Y) )).

cnf(multiply_substitution1,axiom,
    ( ~ equalish(X,Y)
    | equalish(multiply(X,W),multiply(Y,W)) )).

cnf(multiply_substitution2,axiom,
    ( ~ equalish(X,Y)
    | equalish(multiply(W,X),multiply(W,Y)) )).

cnf(product_substitution1,axiom,
    ( ~ equalish(X,Y)
    | ~ product(X,W,Z)
    | product(Y,W,Z) )).

cnf(product_substitution2,axiom,
    ( ~ equalish(X,Y)
    | ~ product(W,X,Z)
    | product(W,Y,Z) )).

cnf(product_substitution3,axiom,
    ( ~ equalish(X,Y)
    | ~ product(W,Z,X)
    | product(W,Z,Y) )).

cnf(reflexivity,axiom,
    ( equalish(X,X) )).

%input_clause(symmetry,axiom,
%    [--equalish(X,Y),
%     ++equalish(Y,X)]).

cnf(transitivity,axiom,
    ( ~ equalish(X,Y)
    | ~ equalish(Y,Z)
    | equalish(X,Z) )).

cnf(additive_identity1,axiom,
    ( sum(additive_identity,X,X) )).

cnf(additive_identity2,axiom,
    ( sum(X,additive_identity,X) )).

cnf(closure_of_multiplication,axiom,
    ( product(X,Y,multiply(X,Y)) )).

cnf(closure_of_addition,axiom,
    ( sum(X,Y,add(X,Y)) )).

%input_clause(additive_inverse1,axiom,
%    [++sum(additive_inverse(X),X,additive_identity)]).

%input_clause(additive_inverse2,axiom,
%    [++sum(X,additive_inverse(X),additive_identity)]).

cnf(associativity_of_addition1,axiom,
    ( ~ sum(X,Y,U)
    | ~ sum(Y,Z,V)
    | ~ sum(U,Z,W)
    | sum(X,V,W) )).

cnf(associativity_of_addition2,axiom,
    ( ~ sum(X,Y,U)
    | ~ sum(Y,Z,V)
    | ~ sum(X,V,W)
    | sum(U,Z,W) )).

cnf(commutativity_of_addition,axiom,
    ( ~ sum(X,Y,Z)
    | sum(Y,X,Z) )).

cnf(associativity_of_multiplication1,axiom,
    ( ~ product(X,Y,U)
    | ~ product(Y,Z,V)
    | ~ product(U,Z,W)
    | product(X,V,W) )).

cnf(associativity_of_multiplication2,axiom,
    ( ~ product(X,Y,U)
    | ~ product(Y,Z,V)
    | ~ product(X,V,W)
    | product(U,Z,W) )).

cnf(distributivity1,axiom,
    ( ~ product(X,Y,V1)
    | ~ product(X,Z,V2)
    | ~ sum(Y,Z,V3)
    | ~ product(X,V3,V4)
    | sum(V1,V2,V4) )).

cnf(distributivity2,axiom,
    ( ~ product(X,Y,V1)
    | ~ product(X,Z,V2)
    | ~ sum(Y,Z,V3)
    | ~ sum(V1,V2,V4)
    | product(X,V3,V4) )).

cnf(distributivity3,axiom,
    ( ~ product(Y,X,V1)
    | ~ product(Z,X,V2)
    | ~ sum(Y,Z,V3)
    | ~ product(V3,X,V4)
    | sum(V1,V2,V4) )).

cnf(distributivity4,axiom,
    ( ~ product(Y,X,V1)
    | ~ product(Z,X,V2)
    | ~ sum(Y,Z,V3)
    | ~ sum(V1,V2,V4)
    | product(V3,X,V4) )).

cnf(multiplicative_identity1,axiom,
    ( product(additive_identity,X,additive_identity) )).

cnf(multiplicative_identity2,axiom,
    ( product(X,additive_identity,additive_identity) )).

%-----Equality axioms for operators
cnf(addition_is_well_defined,axiom,
    ( ~ sum(X,Y,U)
    | ~ sum(X,Y,V)
    | equalish(U,V) )).

cnf(multiplication_is_well_defined,axiom,
    ( ~ product(X,Y,U)
    | ~ product(X,Y,V)
    | equalish(U,V) )).

cnf(sum_left_cancellation,axiom,
    ( ~ sum(A,B,C)
    | ~ sum(D,B,C)
    | equalish(D,A) )).

cnf(sum_right_concellation,axiom,
    ( ~ sum(A,B,C)
    | ~ sum(A,D,C)
    | equalish(D,B) )).

cnf(absorbtion1,axiom,
    ( sum(A,add(A,B),B) )).

cnf(absorbtion2,axiom,
    ( sum(add(A,B),B,A) )).

cnf(clause32,axiom,
    ( sum(A,A,additive_identity) )).

cnf(clause33,axiom,
    ( equalish(add(A,additive_identity),A) )).

cnf(clause34,axiom,
    ( equalish(add(A,A),additive_identity) )).

cnf(clause35,axiom,
    ( equalish(multiply(A,A),A) )).

cnf(clause36,axiom,
    ( equalish(multiply(a,b),c) )).

cnf(clause37,axiom,
    ( equalish(multiply(b,a),d) )).

cnf(clause38,axiom,
    ( sum(A,B,add(B,A)) )).

cnf(clause39,axiom,
    ( product(a,c,c) )).

cnf(clause40,axiom,
    ( product(b,d,d) )).

cnf(clause41,axiom,
    ( product(c,b,c) )).

cnf(clause42,axiom,
    ( product(d,a,d) )).

cnf(clause43,axiom,
    ( product(A,multiply(A,B),multiply(A,B)) )).

cnf(clause44,axiom,
    ( product(a,multiply(b,A),multiply(B,A)) )).

cnf(clause45,axiom,
    ( product(a,b,multiply(c,b)) )).

cnf(clause46,axiom,
    ( product(a,multiply(b,c),c) )).

cnf(clause47,axiom,
    ( product(b,multiply(a,A),multiply(d,A)) )).

cnf(clause48,axiom,
    ( product(b,a,multiply(d,a)) )).

cnf(clause49,axiom,
    ( product(b,multiply(a,d),d) )).

cnf(clause50,axiom,
    ( product(b,c,multiply(d,b)) )).

cnf(clause51,axiom,
    ( product(a,d,multiply(c,a)) )).

cnf(clause52,axiom,
    ( product(multiply(A,B),B,multiply(A,B)) )).

cnf(clause53,axiom,
    ( product(multiply(A,a),b,multiply(A,c)) )).

cnf(clause54,axiom,
    ( product(a,b,multiply(a,c)) )).

cnf(clause55,axiom,
    ( product(multiply(c,a),b,c) )).

cnf(clause56,axiom,
    ( product(d,b,multiply(b,c)) )).

cnf(clause57,axiom,
    ( product(multiply(A,b),a,multiply(A,d)) )).

cnf(clause58,axiom,
    ( product(b,a,multiply(b,d)) )).

cnf(clause59,axiom,
    ( product(multiply(d,b),a,d) )).

cnf(clause60,axiom,
    ( product(c,a,multiply(a,d)) )).

cnf(clause63,axiom,
    ( product(a,add(b,a),add(c,a)) )).

cnf(clause64,axiom,
    ( product(a,add(a,b),add(a,c)) )).

cnf(clause65,axiom,
    ( product(b,add(a,b),add(d,b)) )).

cnf(clause66,axiom,
    ( product(b,add(b,a),add(b,d)) )).

cnf(clause67,axiom,
    ( product(add(a,b),b,add(c,b)) )).

cnf(clause68,axiom,
    ( product(add(b,a),b,add(b,c)) )).

cnf(clause69,axiom,
    ( product(add(b,a),a,add(d,a)) )).

cnf(clause70,axiom,
    ( product(add(a,b),a,add(a,d)) )).

cnf(clause71,axiom,
    ( product(A,A,A) )).

cnf(a_times_b,negated_conjecture,
    ( product(a,b,c) )).

cnf(b_times_a,negated_conjecture,
    ( product(b,a,d) )).

cnf(prove_c_equals_d,negated_conjecture,
    ( ~ equalish(c,d) )).

%--------------------------------------------------------------------------
