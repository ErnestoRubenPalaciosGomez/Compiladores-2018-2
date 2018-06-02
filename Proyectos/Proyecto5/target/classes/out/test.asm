.data
x:	.word 0
t:	.word 0
q:	.word 0
p:	.word 0
z:	.word 0
y:	.word 0
.text
li ,$t1 , 5
sw $t1, x
li ,$t2 , 2
li ,$t3 , 3
add $t2, $t2, $t3
sw $t2, y
li ,$t4 , 50
li ,$t5 , 10
sub $t4, $t4, $t5
li ,$t5 , 10
sub $t4, $t4, $t5
sw $t4, z
lw $t5, z
lw $t6, y
lw $t7, x
add $t6, $t6, $t7
div $t5, $t6
mflo $t5
li $v0,1
add  $a0, $zero, $t5
syscall
li ,$t8 , 6
sw $t8, t
li ,$t9 , 20
sw $t9, p
lw $t9, t
lw $t0, p
add $t9, $t9, $t0
li $v0,1
add  $a0, $zero, $t9
syscall
li ,$t1 , 30
sw $t1, q
lw $t1, q
lw $t2, z
add $t1, $t1, $t2
li $v0,1
add  $a0, $zero, $t1
syscall
