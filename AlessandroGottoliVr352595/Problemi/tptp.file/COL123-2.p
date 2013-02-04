%------------------------------------------------------------------------------
% File     : COL123-2 : TPTP v5.3.0. Released v3.2.0.
% Domain   : Combinatory Logic
% Problem  : Problem about combinators
% Version  : [Pau06] axioms : Reduced > Especial.
% English  :

% Refs     : [Pau06] Paulson (2006), Email to G. Sutcliffe
% Source   : [Pau06]
% Names    :

% Status   : Unsatisfiable
% Rating   : 0.06 v5.3.0, 0.10 v5.2.0, 0.08 v5.1.0, 0.06 v5.0.0, 0.07 v4.0.1, 0.00 v3.2.0
% Syntax   : Number of clauses     :    9 (   0 non-Horn;   2 unit;   9 RR)
%            Number of atoms       :   19 (   0 equality)
%            Maximal clause size   :    3 (   2 average)
%            Number of predicates  :    1 (   0 propositional; 3-3 arity)
%            Number of functors    :   11 (   6 constant; 0-4 arity)
%            Number of variables   :   38 (  30 singleton)
%            Maximal term depth    :    3 (   2 average)
% SPC      : CNF_UNS_RFO_NEQ_HRN

% Comments : The problems in the [Pau06] collection each have very many axioms,
%            of which only a small selection are required for the refutation.
%            The mission is to find those few axioms, after which a refutation
%            can be quite easily found. This version has only the necessary
%            axioms.
%------------------------------------------------------------------------------
cnf(cls_Transitive__Closure_Or__into__rtrancl_0,axiom,
    ( ~ c_in(V_p,V_r,tc_prod(T_a,T_a))
    | c_in(V_p,c_Transitive__Closure_Ortrancl(V_r,T_a),tc_prod(T_a,T_a)) )).

cnf(cls_Transitive__Closure_Ortrancl__trans_0,axiom,
    ( ~ c_in(c_Pair(V_b,V_c,T_a,T_a),c_Transitive__Closure_Ortrancl(V_r,T_a),tc_prod(T_a,T_a))
    | ~ c_in(c_Pair(V_a,V_b,T_a,T_a),c_Transitive__Closure_Ortrancl(V_r,T_a),tc_prod(T_a,T_a))
    | c_in(c_Pair(V_a,V_c,T_a,T_a),c_Transitive__Closure_Ortrancl(V_r,T_a),tc_prod(T_a,T_a)) )).

cnf(cls_conjecture_1,negated_conjecture,
    ( c_in(c_Pair(v_y,v_z,t_a,t_a),v_r,tc_prod(t_a,t_a)) )).

cnf(cls_conjecture_2,negated_conjecture,
    ( c_in(c_Pair(v_x,v_xb,t_a,t_a),v_r,tc_prod(t_a,t_a)) )).

cnf(cls_conjecture_3,negated_conjecture,
    ( c_in(c_Pair(V_U,v_xaa(V_U),t_a,t_a),c_Transitive__Closure_Ortrancl(v_r,t_a),tc_prod(t_a,t_a))
    | ~ c_in(c_Pair(v_x,V_U,t_a,t_a),v_r,tc_prod(t_a,t_a)) )).

cnf(cls_conjecture_4,negated_conjecture,
    ( c_in(c_Pair(v_y,v_xaa(V_U),t_a,t_a),v_r,tc_prod(t_a,t_a))
    | ~ c_in(c_Pair(v_x,V_U,t_a,t_a),v_r,tc_prod(t_a,t_a)) )).

cnf(cls_conjecture_5,negated_conjecture,
    ( ~ c_in(c_Pair(v_z,V_U,t_a,t_a),v_r,tc_prod(t_a,t_a))
    | ~ c_in(c_Pair(v_xb,V_U,t_a,t_a),c_Transitive__Closure_Ortrancl(v_r,t_a),tc_prod(t_a,t_a)) )).

cnf(cls_conjecture_6,negated_conjecture,
    ( c_in(c_Pair(V_V,v_xa(V_U,V_V,V_W),t_a,t_a),v_r,tc_prod(t_a,t_a))
    | ~ c_in(c_Pair(V_U,V_W,t_a,t_a),v_r,tc_prod(t_a,t_a))
    | ~ c_in(c_Pair(V_U,V_V,t_a,t_a),v_r,tc_prod(t_a,t_a)) )).

cnf(cls_conjecture_7,negated_conjecture,
    ( c_in(c_Pair(V_W,v_xa(V_U,V_V,V_W),t_a,t_a),v_r,tc_prod(t_a,t_a))
    | ~ c_in(c_Pair(V_U,V_W,t_a,t_a),v_r,tc_prod(t_a,t_a))
    | ~ c_in(c_Pair(V_U,V_V,t_a,t_a),v_r,tc_prod(t_a,t_a)) )).

%------------------------------------------------------------------------------
