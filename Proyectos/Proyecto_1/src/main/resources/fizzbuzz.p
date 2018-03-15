v = 1
t = 5
condicion = True  
        
while condicion: #este es un comentario que no hace nada
    v += 1
    t *= v
   #este es un comentario que no hace nada
    if v >= 100:
        print t
    else:
        print "He terminado de escribir la tabla del 5 !que cansado¡"
        condicion = False