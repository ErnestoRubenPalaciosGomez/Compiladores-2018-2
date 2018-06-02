.data
q:	.word 0
p:	.word 0
d:	.word 0
c:	.word 0
z:	.word 0
y:	.word 0
b:	.word 0
a:	.word 0
x:	.word 0
t:	.word 0
.text
li ,$t1 , 5
sw $t1, x
li ,$t2 , 0
lw $t3, x
add $t2, $t2, $t3
sw $t2, y
lw $t4, x
li ,$t5 , 3
add $t4, $t4, $t5
sw $t4, a
li ,$t6 , 50
lw $t7, x
mult $t6, $t7
mflo $t6
sw $t6, z
li ,$t8 , 50
lw $t9, x
sub $t8, $t8, $t9
sw $t8, b
lw $t0, x
li ,$t1 , 50
sub $t0, $t0, $t1
sw $t0, c
lw $t2, x
lw $t3, y
mult $t2, $t3
mflo $t2
sw $t2, d
lw $t3, z
lw $t4, y
lw $t5, x
add $t4, $t4, $t5
div $t3, $t4
mflo $t3
li $v0,1
add  $a0, $zero, $t3
syscall
li ,$t6 , 6
sw $t6, t
lw $t6, x
lw $t7, t
mult $t6, $t7
mflo $t6
li $v0,1
add  $a0, $zero, $t6
syscall
li ,$t8 , 20
sw $t8, p
lw $t8, t
lw $t9, p
add $t8, $t8, $t9
li $v0,1
add  $a0, $zero, $t8
syscall
li ,$t0 , 30
sw $t0, q
lw $t0, q
lw $t1, z
sub $t0, $t0, $t1
li $v0,1
add  $a0, $zero, $t0
syscall
nop 
