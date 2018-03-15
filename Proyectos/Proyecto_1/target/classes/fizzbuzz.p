v = 1
t = 5
condicion = True

while condicion:
    v += 1
    t *= v
    if v >= 100:
        print t
    else:
        print "He terminado de escribir la tabla del 5 !que cansado¡"
        condicion = False