# Syntax-Interpreter
The starter file is called <b>console.scala</b> which is located at <b>src.ui</b>

It consists of three major parts: Expression, values and UI.
<ul> Value 
<li> Environment </li>
<li> boolean </li>
<li> Closure </li>
<li> Number</li>
</ul>

<ul> Expression 
<li> Function call </li>
<li> Identifier </li>
<li> Special Form </li>
<li> Literal</li>
</ul>

<p>UI contains the console file and the Parser</p>
<p><b>  Supported Operations </b>
<p>Arithmetics:</p>
<p>->2 + 2
<p>-> 4.0
<p>Declare constant:
<p>-> def a = 2
<p>-> ok
<p>-> a
<p>-> 2.0
<p>Declare Variable
<p>-> def a = var(2)
<p>->a = Variable(2)
<p>Logical Expression
<p>-> 2 > 1
<p>-> true
<p>Lambda Expression
<p>-> def function = lambda(x) x * 1
<p>->function(1)
<p>->2.0
<p>Block Expression
<p>-> def function = {def a = 3; a + 1}
<p>->function
<p>->2.0

 
