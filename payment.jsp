<html>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Registration Form</title>
    <!---Custom CSS File--->
    <link rel="stylesheet" href="style.css" />
    
  


    </head>
    <body>
        <section class="container">
            <header>Payement form</header>
            <form action="pay" method="post" class="form">
           
            <% String num=request.getParameter("mobile");
                 session.setAttribute("mobile",num);
             %>
             Mobile No:<%=num%><br>

     
          <label>Fee Payment</label><br>
            <button formaction="pay">pay</button>
            
            </form>
          </section>
    </body>
</html>