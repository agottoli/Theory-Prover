%--------------------------------------------------------------------------
% File     : ANA001-1 : TPTP v5.3.0. Released v1.0.0.
% Domain   : Analysis
% Problem  : Attaining minimum (or maximum) value
% Version  : [WB87] axioms.
% English  : A continuous function f in a closed real interval [a,b]
%            attains its minimum (or maximum) in this interval.

% Refs     : [WB87]  Wang & Bledsoe (1987), Hierarchical Deduction
% Source   : [WB87]
% Names    : AM8 [WB87]

% Status   : Unknown
% Rating   : 1.00 v2.0.0
% Syntax   : Number of clauses     :   18 (   3 non-Horn;   3 unit;  16 RR)
%            Number of atoms       :   41 (   0 equality)
%            Maximal clause size   :    4 (   2 average)
%            Number of predicates  :    2 (   0 propositional; 2-3 arity)
%            Number of functors    :    7 (   3 constant; 0-1 arity)
%            Number of variables   :   23 (   0 singleton)
%            Maximal term depth    :    3 (   1 average)
% SPC      : CNF_UNK_NUE

% Comments :
%--------------------------------------------------------------------------
cnf(refelxivity,axiom,
    ( less_than_or_equal(X,X) )).

cnf(totality,axiom,
    ( less_than_or_equal(X,Y)
    | less_than_or_equal(Y,X) )).

cnf(transitivity,axiom,
    ( less_than_or_equal(X,Z)
    | ~ less_than_or_equal(X,Y)
    | ~ less_than_or_equal(Y,Z) )).

cnf(function,hypothesis,
    ( less_than_or_equal(f(X),f(Y))
    | ~ less_than_or_equal(X,Y)
    | ~ less_than_or_equal(Y,X) )).

cnf(in_interval,hypothesis,
    ( in_interval(Lower,X,Upper)
    | ~ less_than_or_equal(Lower,X)
    | ~ less_than_or_equal(X,Upper) )).

cnf(interval1,hypothesis,
    ( less_than_or_equal(lower_bound,extreme_point) )).

cnf(interval2,hypothesis,
    ( less_than_or_equal(extreme_point,upper_bound) )).

cnf(below_extreme_point,hypothesis,
    ( less_than_or_equal(f(extreme_point),f(X))
    | ~ in_interval(lower_bound,X,extreme_point) )).

cnf(q_function1,hypothesis,
    ( less_than_or_equal(X,extreme_point)
    | ~ in_interval(lower_bound,X,upper_bound)
    | less_than_or_equal(lower_bound,q(X)) )).

cnf(q_function2,hypothesis,
    ( less_than_or_equal(X,extreme_point)
    | ~ in_interval(lower_bound,X,upper_bound)
    | ~ less_than_or_equal(f(X),q(X)) )).

cnf(q_function3,hypothesis,
    ( less_than_or_equal(X,extreme_point)
    | ~ in_interval(lower_bound,X,upper_bound)
    | less_than_or_equal(q(X),X) )).

cnf(h_function1,hypothesis,
    ( less_than_or_equal(lower_bound,h(X))
    | ~ in_interval(lower_bound,X,upper_bound) )).

cnf(h_function2,hypothesis,
    ( less_than_or_equal(h(X),upper_bound)
    | ~ in_interval(lower_bound,X,upper_bound) )).

cnf(h_function3,hypothesis,
    ( less_than_or_equal(f(h(X)),f(X))
    | ~ in_interval(lower_bound,X,upper_bound) )).

cnf(h_function4,hypothesis,
    ( less_than_or_equal(h(X),Y)
    | ~ in_interval(lower_bound,X,upper_bound)
    | ~ in_interval(lower_bound,Y,upper_bound)
    | ~ less_than_or_equal(f(Y),f(X)) )).

cnf(k_function1,hypothesis,
    ( ~ in_interval(lower_bound,X,upper_bound)
    | less_than_or_equal(lower_bound,k(X)) )).

cnf(k_function2,hypothesis,
    ( ~ in_interval(lower_bound,X,upper_bound)
    | less_than_or_equal(k(X),upper_bound) )).

cnf(prove_something,negated_conjecture,
    ( ~ less_than_or_equal(f(X),f(k(X)))
    | ~ in_interval(lower_bound,X,upper_bound) )).

%--------------------------------------------------------------------------
