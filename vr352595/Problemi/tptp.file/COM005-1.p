%--------------------------------------------------------------------------
% File     : COM005-1 : TPTP v5.3.0. Released v2.7.0.
% Domain   : Computing Theory
% Problem  : Behaviour of an algorithm that orients rings with 3 nodes
% Version  : Especial.
% English  :

% Refs     : [Hoe94] Hoepman (1994), Uniform Deterministic Self-Stabilizing
%          : [Kon03] Konev (2003), Email to G. Sutcliffe
% Source   : [Kon03]
% Names    :

% Status   : Unsatisfiable
% Rating   : 0.80 v5.3.0, 0.70 v5.2.0, 0.60 v5.1.0, 0.64 v4.1.0, 0.62 v4.0.1, 0.40 v4.0.0, 0.57 v3.4.0, 0.75 v3.3.0, 0.67 v3.2.0, 0.33 v3.1.0, 0.67 v2.7.0
% Syntax   : Number of clauses     :  268 ( 158 non-Horn;   1 unit; 266 RR)
%            Number of atoms       : 1366 (   0 equality)
%            Maximal clause size   :    8 (   5 average)
%            Number of predicates  :   24 (   0 propositional; 1-1 arity)
%            Number of functors    :    2 (   1 constant; 0-1 arity)
%            Number of variables   :  267 (   0 singleton)
%            Maximal term depth    :    2 (   1 average)
% SPC      : CNF_UNS_RFO_NEQ_NHN

% Comments :
%--------------------------------------------------------------------------
cnf(c001,negated_conjecture,
    ( ap(a) )).

cnf(c002,negated_conjecture,
    ( ~ ap(X)
    | ~ aq(X) )).

cnf(c003,negated_conjecture,
    ( ~ ap(X)
    | ~ ar(X) )).

cnf(c004,negated_conjecture,
    ( ~ aq(X)
    | ~ ar(X) )).

cnf(c005,negated_conjecture,
    ( ap(X)
    | aq(X)
    | ar(X) )).

cnf(c006,negated_conjecture,
    ( ~ pqr1(X)
    | ~ p(X) )).

cnf(c007,negated_conjecture,
    ( ~ pqr1(X)
    | pp(X) )).

cnf(c008,negated_conjecture,
    ( ~ pqr1(X)
    | ~ q(X) )).

cnf(c009,negated_conjecture,
    ( ~ pqr1(X)
    | ~ qq(X) )).

cnf(c010,negated_conjecture,
    ( ~ pqr1(X)
    | r(X) )).

cnf(c011,negated_conjecture,
    ( ~ pqr1(X)
    | ~ rr(X) )).

cnf(c012,negated_conjecture,
    ( ~ pqr1(X)
    | aq(X) )).

cnf(c013,negated_conjecture,
    ( ~ pqr2(X)
    | p(X) )).

cnf(c014,negated_conjecture,
    ( ~ pqr2(X)
    | pp(X) )).

cnf(c015,negated_conjecture,
    ( ~ pqr2(X)
    | q(X) )).

cnf(c016,negated_conjecture,
    ( ~ pqr2(X)
    | ~ qq(X) )).

cnf(c017,negated_conjecture,
    ( ~ pqr2(X)
    | ~ r(X) )).

cnf(c018,negated_conjecture,
    ( ~ pqr2(X)
    | ~ rr(X) )).

cnf(c019,negated_conjecture,
    ( ~ pqr2(X)
    | aq(X) )).

cnf(c020,negated_conjecture,
    ( ~ pqr3(X)
    | p(X) )).

cnf(c021,negated_conjecture,
    ( ~ pqr3(X)
    | ~ pp(X) )).

cnf(c022,negated_conjecture,
    ( ~ pqr3(X)
    | ~ q(X) )).

cnf(c023,negated_conjecture,
    ( ~ pqr3(X)
    | ~ qq(X) )).

cnf(c024,negated_conjecture,
    ( ~ pqr3(X)
    | ~ r(X) )).

cnf(c025,negated_conjecture,
    ( ~ pqr3(X)
    | rr(X) )).

cnf(c026,negated_conjecture,
    ( ~ pqr3(X)
    | aq(X) )).

cnf(c027,negated_conjecture,
    ( ~ pqr4(X)
    | ~ p(X) )).

cnf(c028,negated_conjecture,
    ( ~ pqr4(X)
    | ~ pp(X) )).

cnf(c029,negated_conjecture,
    ( ~ pqr4(X)
    | q(X) )).

cnf(c030,negated_conjecture,
    ( ~ pqr4(X)
    | ~ qq(X) )).

cnf(c031,negated_conjecture,
    ( ~ pqr4(X)
    | r(X) )).

cnf(c032,negated_conjecture,
    ( ~ pqr4(X)
    | rr(X) )).

cnf(c033,negated_conjecture,
    ( ~ pqr4(X)
    | aq(X) )).

cnf(c034,negated_conjecture,
    ( ~ qrp1(X)
    | ~ q(X) )).

cnf(c035,negated_conjecture,
    ( ~ qrp1(X)
    | qq(X) )).

cnf(c036,negated_conjecture,
    ( ~ qrp1(X)
    | ~ r(X) )).

cnf(c037,negated_conjecture,
    ( ~ qrp1(X)
    | ~ rr(X) )).

cnf(c038,negated_conjecture,
    ( ~ qrp1(X)
    | p(X) )).

cnf(c039,negated_conjecture,
    ( ~ qrp1(X)
    | ~ pp(X) )).

cnf(c040,negated_conjecture,
    ( ~ qrp1(X)
    | ar(X) )).

cnf(c041,negated_conjecture,
    ( ~ qrp2(X)
    | q(X) )).

cnf(c042,negated_conjecture,
    ( ~ qrp2(X)
    | qq(X) )).

cnf(c043,negated_conjecture,
    ( ~ qrp2(X)
    | r(X) )).

cnf(c044,negated_conjecture,
    ( ~ qrp2(X)
    | ~ rr(X) )).

cnf(c045,negated_conjecture,
    ( ~ qrp2(X)
    | ~ p(X) )).

cnf(c046,negated_conjecture,
    ( ~ qrp2(X)
    | ~ pp(X) )).

cnf(c047,negated_conjecture,
    ( ~ qrp2(X)
    | ar(X) )).

cnf(c048,negated_conjecture,
    ( ~ qrp3(X)
    | q(X) )).

cnf(c049,negated_conjecture,
    ( ~ qrp3(X)
    | ~ qq(X) )).

cnf(c050,negated_conjecture,
    ( ~ qrp3(X)
    | ~ r(X) )).

cnf(c051,negated_conjecture,
    ( ~ qrp3(X)
    | ~ rr(X) )).

cnf(c052,negated_conjecture,
    ( ~ qrp3(X)
    | ~ p(X) )).

cnf(c053,negated_conjecture,
    ( ~ qrp3(X)
    | pp(X) )).

cnf(c054,negated_conjecture,
    ( ~ qrp3(X)
    | ar(X) )).

cnf(c055,negated_conjecture,
    ( ~ qrp4(X)
    | ~ q(X) )).

cnf(c056,negated_conjecture,
    ( ~ qrp4(X)
    | ~ qq(X) )).

cnf(c057,negated_conjecture,
    ( ~ qrp4(X)
    | r(X) )).

cnf(c058,negated_conjecture,
    ( ~ qrp4(X)
    | ~ rr(X) )).

cnf(c059,negated_conjecture,
    ( ~ qrp4(X)
    | p(X) )).

cnf(c060,negated_conjecture,
    ( ~ qrp4(X)
    | pp(X) )).

cnf(c061,negated_conjecture,
    ( ~ qrp4(X)
    | ar(X) )).

cnf(c062,negated_conjecture,
    ( ~ rpq1(X)
    | ~ r(X) )).

cnf(c063,negated_conjecture,
    ( ~ rpq1(X)
    | rr(X) )).

cnf(c064,negated_conjecture,
    ( ~ rpq1(X)
    | ~ p(X) )).

cnf(c065,negated_conjecture,
    ( ~ rpq1(X)
    | ~ pp(X) )).

cnf(c066,negated_conjecture,
    ( ~ rpq1(X)
    | q(X) )).

cnf(c067,negated_conjecture,
    ( ~ rpq1(X)
    | ~ qq(X) )).

cnf(c068,negated_conjecture,
    ( ~ rpq1(X)
    | ap(X) )).

cnf(c069,negated_conjecture,
    ( ~ rpq2(X)
    | r(X) )).

cnf(c070,negated_conjecture,
    ( ~ rpq2(X)
    | rr(X) )).

cnf(c071,negated_conjecture,
    ( ~ rpq2(X)
    | p(X) )).

cnf(c072,negated_conjecture,
    ( ~ rpq2(X)
    | ~ pp(X) )).

cnf(c073,negated_conjecture,
    ( ~ rpq2(X)
    | ~ q(X) )).

cnf(c074,negated_conjecture,
    ( ~ rpq2(X)
    | ~ qq(X) )).

cnf(c075,negated_conjecture,
    ( ~ rpq2(X)
    | ap(X) )).

cnf(c076,negated_conjecture,
    ( ~ rpq3(X)
    | r(X) )).

cnf(c077,negated_conjecture,
    ( ~ rpq3(X)
    | ~ rr(X) )).

cnf(c078,negated_conjecture,
    ( ~ rpq3(X)
    | ~ p(X) )).

cnf(c079,negated_conjecture,
    ( ~ rpq3(X)
    | ~ pp(X) )).

cnf(c080,negated_conjecture,
    ( ~ rpq3(X)
    | ~ q(X) )).

cnf(c081,negated_conjecture,
    ( ~ rpq3(X)
    | qq(X) )).

cnf(c082,negated_conjecture,
    ( ~ rpq3(X)
    | ap(X) )).

cnf(c083,negated_conjecture,
    ( ~ rpq4(X)
    | ~ r(X) )).

cnf(c084,negated_conjecture,
    ( ~ rpq4(X)
    | ~ rr(X) )).

cnf(c085,negated_conjecture,
    ( ~ rpq4(X)
    | p(X) )).

cnf(c086,negated_conjecture,
    ( ~ rpq4(X)
    | ~ pp(X) )).

cnf(c087,negated_conjecture,
    ( ~ rpq4(X)
    | q(X) )).

cnf(c088,negated_conjecture,
    ( ~ rpq4(X)
    | qq(X) )).

cnf(c089,negated_conjecture,
    ( ~ rpq4(X)
    | ap(X) )).

cnf(c090,negated_conjecture,
    ( ~ qp(X)
    | ~ rq(X)
    | ~ pr(X) )).

cnf(c091,negated_conjecture,
    ( qp(X)
    | rq(X)
    | pr(X) )).

cnf(c092,negated_conjecture,
    ( ~ ap(X)
    | aq(s(X)) )).

cnf(c093,negated_conjecture,
    ( ~ aq(X)
    | ar(s(X)) )).

cnf(c094,negated_conjecture,
    ( ~ ar(X)
    | ap(s(X)) )).

cnf(c095,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | q(X)
    | r(X)
    | q(s(X)) )).

cnf(c096,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | q(X)
    | r(X)
    | ~ qq(s(X)) )).

cnf(c097,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | r(X)
    | q(s(X)) )).

cnf(c098,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | r(X)
    | ~ qq(s(X)) )).

cnf(c099,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ q(X)
    | ~ r(X)
    | ~ q(s(X)) )).

cnf(c100,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ q(X)
    | ~ r(X)
    | ~ qq(s(X)) )).

cnf(c101,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | ~ r(X)
    | ~ q(s(X)) )).

cnf(c102,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | ~ r(X)
    | ~ qq(s(X)) )).

cnf(c103,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | ~ q(s(X)) )).

cnf(c104,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | qq(s(X)) )).

cnf(c105,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | ~ qq(X)
    | ~ r(X)
    | ~ q(s(X)) )).

cnf(c106,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | ~ qq(X)
    | ~ r(X)
    | ~ qq(s(X)) )).

cnf(c107,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | q(s(X)) )).

cnf(c108,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | qq(s(X)) )).

cnf(c109,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | ~ qq(X)
    | r(X)
    | q(s(X)) )).

cnf(c110,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | ~ qq(X)
    | r(X)
    | ~ qq(s(X)) )).

cnf(c111,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | ~ q(s(X)) )).

cnf(c112,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | qq(s(X)) )).

cnf(c113,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | ~ rr(X)
    | ~ q(s(X)) )).

cnf(c114,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | ~ rr(X)
    | ~ qq(s(X)) )).

cnf(c115,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | q(s(X)) )).

cnf(c116,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | qq(s(X)) )).

cnf(c117,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | ~ rr(X)
    | q(s(X)) )).

cnf(c118,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ qq(s(X)) )).

cnf(c119,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | r(X)
    | q(s(X)) )).

cnf(c120,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | r(X)
    | qq(s(X)) )).

cnf(c121,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | ~ q(s(X)) )).

cnf(c122,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | qq(s(X)) )).

cnf(c123,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | q(s(X)) )).

cnf(c124,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | qq(s(X)) )).

cnf(c125,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | ~ r(X)
    | ~ q(s(X)) )).

cnf(c126,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | ~ r(X)
    | qq(s(X)) )).

cnf(c127,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | q(s(X)) )).

cnf(c128,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | ~ qq(s(X)) )).

cnf(c129,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | ~ q(s(X)) )).

cnf(c130,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | ~ qq(s(X)) )).

cnf(c131,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | q(s(X)) )).

cnf(c132,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ qq(s(X)) )).

cnf(c133,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ q(s(X)) )).

cnf(c134,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ qq(s(X)) )).

cnf(c135,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | q(s(X)) )).

cnf(c136,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | qq(s(X)) )).

cnf(c137,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | pr(s(X)) )).

cnf(c138,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | q(s(X)) )).

cnf(c139,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | qq(s(X)) )).

cnf(c140,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | ~ pr(s(X)) )).

cnf(c141,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | ~ q(s(X)) )).

cnf(c142,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | qq(s(X)) )).

cnf(c143,negated_conjecture,
    ( ~ aq(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | pr(s(X)) )).

cnf(c144,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ q(s(X)) )).

cnf(c145,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | qq(s(X)) )).

cnf(c146,negated_conjecture,
    ( ~ aq(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ pr(s(X)) )).

cnf(c147,negated_conjecture,
    ( aq(X)
    | q(X)
    | ~ q(s(X)) )).

cnf(c148,negated_conjecture,
    ( aq(X)
    | ~ q(X)
    | q(s(X)) )).

cnf(c149,negated_conjecture,
    ( aq(X)
    | qq(X)
    | ~ qq(s(X)) )).

cnf(c150,negated_conjecture,
    ( aq(X)
    | ~ qq(X)
    | qq(s(X)) )).

cnf(c151,negated_conjecture,
    ( pqr1(X)
    | pqr2(X)
    | pqr3(X)
    | pqr4(X)
    | ~ pr(X)
    | pr(s(X)) )).

cnf(c152,negated_conjecture,
    ( pqr1(X)
    | pqr2(X)
    | pqr3(X)
    | pqr4(X)
    | pr(X)
    | ~ pr(s(X)) )).

cnf(c153,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | r(X)
    | p(X)
    | r(s(X)) )).

cnf(c154,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | r(X)
    | p(X)
    | ~ rr(s(X)) )).

cnf(c155,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | p(X)
    | r(s(X)) )).

cnf(c156,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | p(X)
    | ~ rr(s(X)) )).

cnf(c157,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ r(X)
    | ~ p(X)
    | ~ r(s(X)) )).

cnf(c158,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ r(X)
    | ~ p(X)
    | ~ rr(s(X)) )).

cnf(c159,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | ~ p(X)
    | ~ r(s(X)) )).

cnf(c160,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | ~ p(X)
    | ~ rr(s(X)) )).

cnf(c161,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | ~ r(s(X)) )).

cnf(c162,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | rr(s(X)) )).

cnf(c163,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | ~ rr(X)
    | ~ p(X)
    | ~ r(s(X)) )).

cnf(c164,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | ~ rr(X)
    | ~ p(X)
    | ~ rr(s(X)) )).

cnf(c165,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | r(s(X)) )).

cnf(c166,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | rr(s(X)) )).

cnf(c167,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | ~ rr(X)
    | p(X)
    | r(s(X)) )).

cnf(c168,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | ~ rr(X)
    | p(X)
    | ~ rr(s(X)) )).

cnf(c169,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | ~ r(s(X)) )).

cnf(c170,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | rr(s(X)) )).

cnf(c171,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | ~ pp(X)
    | ~ r(s(X)) )).

cnf(c172,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | ~ pp(X)
    | ~ rr(s(X)) )).

cnf(c173,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | r(s(X)) )).

cnf(c174,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | rr(s(X)) )).

cnf(c175,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | ~ pp(X)
    | r(s(X)) )).

cnf(c176,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ rr(s(X)) )).

cnf(c177,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | p(X)
    | r(s(X)) )).

cnf(c178,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | qq(X)
    | ~ r(X)
    | ~ rr(X)
    | p(X)
    | rr(s(X)) )).

cnf(c179,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | ~ r(s(X)) )).

cnf(c180,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | rr(s(X)) )).

cnf(c181,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | r(s(X)) )).

cnf(c182,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | rr(s(X)) )).

cnf(c183,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | ~ p(X)
    | ~ r(s(X)) )).

cnf(c184,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | qq(X)
    | r(X)
    | ~ rr(X)
    | ~ p(X)
    | rr(s(X)) )).

cnf(c185,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | r(s(X)) )).

cnf(c186,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | ~ rr(s(X)) )).

cnf(c187,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | ~ r(s(X)) )).

cnf(c188,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | ~ rr(s(X)) )).

cnf(c189,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | r(s(X)) )).

cnf(c190,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ rr(s(X)) )).

cnf(c191,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ r(s(X)) )).

cnf(c192,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ rr(s(X)) )).

cnf(c193,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | r(s(X)) )).

cnf(c194,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | rr(s(X)) )).

cnf(c195,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | ~ qq(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | qp(s(X)) )).

cnf(c196,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | r(s(X)) )).

cnf(c197,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | rr(s(X)) )).

cnf(c198,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | qq(X)
    | r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | ~ qp(s(X)) )).

cnf(c199,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | ~ r(s(X)) )).

cnf(c200,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | rr(s(X)) )).

cnf(c201,negated_conjecture,
    ( ~ ar(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | qp(s(X)) )).

cnf(c202,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ r(s(X)) )).

cnf(c203,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | rr(s(X)) )).

cnf(c204,negated_conjecture,
    ( ~ ar(X)
    | q(X)
    | qq(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ qp(s(X)) )).

cnf(c205,negated_conjecture,
    ( ar(X)
    | r(X)
    | ~ r(s(X)) )).

cnf(c206,negated_conjecture,
    ( ar(X)
    | ~ r(X)
    | r(s(X)) )).

cnf(c207,negated_conjecture,
    ( ar(X)
    | rr(X)
    | ~ rr(s(X)) )).

cnf(c208,negated_conjecture,
    ( ar(X)
    | ~ rr(X)
    | rr(s(X)) )).

cnf(c209,negated_conjecture,
    ( qrp1(X)
    | qrp2(X)
    | qrp3(X)
    | qrp4(X)
    | ~ qp(X)
    | qp(s(X)) )).

cnf(c210,negated_conjecture,
    ( qrp1(X)
    | qrp2(X)
    | qrp3(X)
    | qrp4(X)
    | qp(X)
    | ~ qp(s(X)) )).

cnf(c211,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | p(X)
    | q(X)
    | p(s(X)) )).

cnf(c212,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | p(X)
    | q(X)
    | ~ pp(s(X)) )).

cnf(c213,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | q(X)
    | p(s(X)) )).

cnf(c214,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | q(X)
    | ~ pp(s(X)) )).

cnf(c215,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ p(X)
    | ~ q(X)
    | ~ p(s(X)) )).

cnf(c216,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ p(X)
    | ~ q(X)
    | ~ pp(s(X)) )).

cnf(c217,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | ~ q(X)
    | ~ p(s(X)) )).

cnf(c218,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | ~ q(X)
    | ~ pp(s(X)) )).

cnf(c219,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | ~ p(s(X)) )).

cnf(c220,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | pp(s(X)) )).

cnf(c221,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | ~ pp(X)
    | ~ q(X)
    | ~ p(s(X)) )).

cnf(c222,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | ~ pp(X)
    | ~ q(X)
    | ~ pp(s(X)) )).

cnf(c223,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | p(s(X)) )).

cnf(c224,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | pp(s(X)) )).

cnf(c225,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | ~ pp(X)
    | q(X)
    | p(s(X)) )).

cnf(c226,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | ~ pp(X)
    | q(X)
    | ~ pp(s(X)) )).

cnf(c227,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | ~ p(s(X)) )).

cnf(c228,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | pp(s(X)) )).

cnf(c229,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | ~ qq(X)
    | ~ p(s(X)) )).

cnf(c230,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | ~ qq(X)
    | ~ pp(s(X)) )).

cnf(c231,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | p(s(X)) )).

cnf(c232,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | pp(s(X)) )).

cnf(c233,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | ~ qq(X)
    | p(s(X)) )).

cnf(c234,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ pp(s(X)) )).

cnf(c235,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | q(X)
    | p(s(X)) )).

cnf(c236,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | rr(X)
    | ~ p(X)
    | ~ pp(X)
    | q(X)
    | pp(s(X)) )).

cnf(c237,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | ~ p(s(X)) )).

cnf(c238,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | p(X)
    | ~ pp(X)
    | q(X)
    | qq(X)
    | pp(s(X)) )).

cnf(c239,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | p(s(X)) )).

cnf(c240,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ p(X)
    | ~ pp(X)
    | ~ q(X)
    | qq(X)
    | pp(s(X)) )).

cnf(c241,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | ~ q(X)
    | ~ p(s(X)) )).

cnf(c242,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | rr(X)
    | p(X)
    | ~ pp(X)
    | ~ q(X)
    | pp(s(X)) )).

cnf(c243,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | p(s(X)) )).

cnf(c244,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | ~ pp(s(X)) )).

cnf(c245,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | ~ p(s(X)) )).

cnf(c246,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | ~ pp(s(X)) )).

cnf(c247,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | p(s(X)) )).

cnf(c248,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ pp(s(X)) )).

cnf(c249,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ p(s(X)) )).

cnf(c250,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ pp(s(X)) )).

cnf(c251,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | p(s(X)) )).

cnf(c252,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | pp(s(X)) )).

cnf(c253,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | ~ rr(X)
    | p(X)
    | pp(X)
    | ~ q(X)
    | qq(X)
    | rq(s(X)) )).

cnf(c254,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | p(s(X)) )).

cnf(c255,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | pp(s(X)) )).

cnf(c256,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | rr(X)
    | p(X)
    | pp(X)
    | q(X)
    | ~ qq(X)
    | ~ rq(s(X)) )).

cnf(c257,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | ~ p(s(X)) )).

cnf(c258,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | pp(s(X)) )).

cnf(c259,negated_conjecture,
    ( ~ ap(X)
    | ~ r(X)
    | ~ rr(X)
    | ~ p(X)
    | pp(X)
    | q(X)
    | qq(X)
    | rq(s(X)) )).

cnf(c260,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ p(s(X)) )).

cnf(c261,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | pp(s(X)) )).

cnf(c262,negated_conjecture,
    ( ~ ap(X)
    | r(X)
    | rr(X)
    | ~ p(X)
    | pp(X)
    | ~ q(X)
    | ~ qq(X)
    | ~ rq(s(X)) )).

cnf(c263,negated_conjecture,
    ( ap(X)
    | p(X)
    | ~ p(s(X)) )).

cnf(c264,negated_conjecture,
    ( ap(X)
    | ~ p(X)
    | p(s(X)) )).

cnf(c265,negated_conjecture,
    ( ap(X)
    | pp(X)
    | ~ pp(s(X)) )).

cnf(c266,negated_conjecture,
    ( ap(X)
    | ~ pp(X)
    | pp(s(X)) )).

cnf(c267,negated_conjecture,
    ( rpq1(X)
    | rpq2(X)
    | rpq3(X)
    | rpq4(X)
    | ~ rq(X)
    | rq(s(X)) )).

cnf(c268,negated_conjecture,
    ( rpq1(X)
    | rpq2(X)
    | rpq3(X)
    | rpq4(X)
    | rq(X)
    | ~ rq(s(X)) )).

%--------------------------------------------------------------------------
