cnf(mp_positive_number_when_appear_20,axiom,( ~ environment(A)
| greater(number_of_organizations(e,appear(an_organisation,A)),zero) )).

cnf(mp_number_mean_non_empty_21,axiom,( ~ environment(A)
| ~ greater(number_of_organizations(A,B),zero)
| subpopulation(sk1(B,A),A,B) )).

cnf(mp_number_mean_non_empty_22,axiom,( ~ environment(A)
| ~ greater(number_of_organizations(A,B),zero)
| greater(cardinality_at_time(sk1(B,A),B),zero) )).
