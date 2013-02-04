%--------------------------------------------------------------------------
% File     : COM001-1 : TPTP v5.3.0. Released v1.0.0.
% Domain   : Computing Theory
% Problem  : A program correctness theorem
% Version  : Especial.
% English  : A simple computing state space, with four states - P3, P4,
%            P5, and P8 (the full version of this state space is in the
%            problem COM002-1). There is a branch at P3 such that the
%            following state is either P4 or P8. P8 has a loop back to P3,
%            while P4 leads to termination. The problem is to show that
%            there is a loop in the computation, passing through P3.

% Refs     : [RR+72] Reboh et al. (1972), Study of automatic theorem provin
%          : [WM76]  Wilson & Minker (1976), Resolution, Refinements, and S
% Source   : [SPRFN]
% Names    : SHORTBURST [RR+72]
%          : SHORTBURST [WM76]

% Status   : Unsatisfiable
% Rating   : 0.06 v5.3.0, 0.05 v5.2.0, 0.00 v2.0.0
% Syntax   : Number of clauses     :   11 (   0 non-Horn;   7 unit;  11 RR)
%            Number of atoms       :   17 (   0 equality)
%            Maximal clause size   :    3 (   2 average)
%            Number of predicates  :    4 (   0 propositional; 2-2 arity)
%            Number of functors    :   11 (   8 constant; 0-2 arity)
%            Number of variables   :   11 (   1 singleton)
%            Maximal term depth    :    3 (   1 average)
% SPC      : CNF_UNS_RFO_NEQ_HRN

% Comments : I suspect this problem was originally by R.M. Burstall.
%--------------------------------------------------------------------------
cnf(direct_success,axiom,
    ( succeeds(Goal_state,Start_state)
    | ~ follows(Goal_state,Start_state) )).

cnf(transitivity_of_success,axiom,
    ( succeeds(Goal_state,Start_state)
    | ~ succeeds(Goal_state,Intermediate_state)
    | ~ succeeds(Intermediate_state,Start_state) )).

cnf(goto_success,axiom,
    ( succeeds(Goal_state,Start_state)
    | ~ has(Start_state,goto(Label))
    | ~ labels(Label,Goal_state) )).

cnf(conditional_success,axiom,
    ( succeeds(Goal_state,Start_state)
    | ~ has(Start_state,ifthen(Condition,Goal_state)) )).

cnf(label_state_3,hypothesis,
    ( labels(loop,p3) )).

cnf(state_3,hypothesis,
    ( has(p3,ifthen(equal_function(register_j,n),p4)) )).

cnf(state_4,hypothesis,
    ( has(p4,goto(out)) )).

cnf(transition_4_to_5,hypothesis,
    ( follows(p5,p4) )).

cnf(transition_3_to_8,hypothesis,
    ( follows(p8,p3) )).

cnf(state_8,hypothesis,
    ( has(p8,goto(loop)) )).

cnf(prove_there_is_a_loop_through_p3,negated_conjecture,
    ( ~ succeeds(p3,p3) )).

%--------------------------------------------------------------------------
