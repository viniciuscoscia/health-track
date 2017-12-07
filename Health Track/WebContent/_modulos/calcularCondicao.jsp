<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:choose>
	<c:when test = "${imc == 0}">
      Sem registro de pesos anteriores
   </c:when>
	
   <c:when test = "${imc < 16}">
      Magreza grave
   </c:when>
   
   <c:when test = "${imc < 17}">
      Magreza moderada
   </c:when>
   
   <c:when test = "${imc < 18.5}">
      Magreza leve
   </c:when>
   
   <c:when test = "${imc < 25}">
      Peso ideal
   </c:when>
   
   <c:when test = "${imc < 30}">
      Sobrepeso
   </c:when>
   
   <c:when test = "${imc < 35}">
      Obesidade Grau I
   </c:when>
   
   <c:when test = "${imc < 40}">
      Obesidade Grau II
   </c:when>
   
   <c:when test = "${imc > 40}">
      Obesidade Grau III
   </c:when>
   
</c:choose>