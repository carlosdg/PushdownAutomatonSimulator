# Non Deterministic Pushdown Automata Simulator

In Computer Science we try to give answers to the following questions:

- What problems can we solve with an algorithm?
- How do we design an algorithm to solve a problem?
- Does the designed algorithm really solve the problem? Is there any error?
- How efficient is the algorithm in terms of time consumption and space needed?

In order to help with the study of algorithms we use mathematical models of computation to represent them.
These models are given an input string and they can recognize it as part of the language that they represent or not. 

Lets say, for example, that our problem is determining whether an input string is made of a number of ones 
followed by the same number of zeroes or not. We can define the language made by all those strings as

_L={1<sup>n</sup>0<sup>n</sup> / n > 0}_.
 
We can now solve the problem by determining whether an input string is part of the language or not. For that we can
use a mathematical model to represent that language, a Pushdown Automaton for example. And how that model is
made represents the algorithm used to solve the problem. 

In case of more complex languages we'll need to use more complex models to represent them. Or, in case that our
problem is not a decision problem we'll need an output string, Turing Machines can transform the input and thus
have an output associated with input strings. Turing Machines are simple models but can represent any computable algorithm.

A Pushdown Automaton (PDA) is a model that represent Context-Free languages, these languages are less rich than the 
ones that Turing Machine represents but they are widely used, for example for defining programming languages.

PDA are formally described by a 7-uple (Q, Σ, Γ, s, Z, δ, F):

- __Q__ is the finite set of states of the automaton.
- __Σ__ is called the input alphabet. It is the finite set of symbols that the input strings can be made of.  
- __Γ__ is called the stack alphabet. It is the finite set of symbols that the stack can have in it.
- __s__ is the starting state of the automaton (_s ∈ Q_).
- __Z__ is the symbol at the top of the stack at the start (_Z ∈ Γ_).
- __δ__ is the transition function. _δ: Q x (Σ ⋃ ε) x Γ → P(Q x Γ<sup>*</sup>)_. Given the current state, taking or not 
the current symbol of the input tape and taking symbol at the top of the stack, the transition function returns
the set of possible states and symbols to push to the stack (note that a single stack symbol must be pop every
time but 0 or more can be pushed).
- __F__ is the set of accepting states (_F ⊆ Q_).