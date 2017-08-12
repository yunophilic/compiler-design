#-------------------------------------
# i		INT	0	0
# j		INT	0	16
# 5		INT	0	32
# n		INT	5	48
# k		BOOLEAN	0	64
# 10		INT	0	80
# l		INT	0	96
# true		BOOLEAN	0	112
# m		BOOLEAN	0	128
# L_1		LABEL	0	0
# L_2		LABEL	0	32
# L_3		LABEL	0	48
# "%d\n"		STR	0	64
# L_4		LABEL	0	80
# L_5		LABEL	0	128
# L_6		LABEL	0	144
# L_7		LABEL	0	160
# L_8		LABEL	0	0
# L_9		LABEL	0	48
# L_10		LABEL	0	80
# L_11		LABEL	0	112
# L_12		LABEL	0	128
# "i = j = %d\n"		STR	0	144
# L_13		LABEL	0	160
# L_14		LABEL	0	192
# L_15		LABEL	0	208
# L_16		LABEL	0	256
# L_17		LABEL	0	272
# L_18		LABEL	0	288
# "i += j = %d\n"		STR	0	304
# L_19		LABEL	0	320
# L_20		LABEL	0	336
# L_21		LABEL	0	352
# L_22		LABEL	0	400
# L_23		LABEL	0	416
# L_24		LABEL	0	432
# "i -= j = %d\n"		STR	0	448
# L_25		LABEL	0	464
# L_26		LABEL	0	480
# L_27		LABEL	0	496
# L_28		LABEL	0	544
# L_29		LABEL	0	560
# "i = j + 10 = %d\n"		STR	0	576
# L_30		LABEL	0	592
# L_31		LABEL	0	608
# L_32		LABEL	0	624
# L_33		LABEL	0	656
# L_34		LABEL	0	672
# "i = j - i = %d\n"		STR	0	688
# L_35		LABEL	0	704
# L_36		LABEL	0	720
# L_37		LABEL	0	736
# L_38		LABEL	0	784
# L_39		LABEL	0	800
# "i = i * 2 = %d\n"		STR	0	816
# L_40		LABEL	0	832
# L_41		LABEL	0	848
# L_42		LABEL	0	864
# L_43		LABEL	0	896
# L_44		LABEL	0	912
# "i = i / j = %d\n"		STR	0	928
# L_45		LABEL	0	944
# L_46		LABEL	0	960
# L_47		LABEL	0	976
# L_48		LABEL	0	1040
# L_49		LABEL	0	1072
# L_50		LABEL	0	1088
# L_51		LABEL	0	1104
# "i = i % 3 = %d\n"		STR	0	1120
# L_52		LABEL	0	1136
# L_53		LABEL	0	1152
# L_54		LABEL	0	1168
# L_55		LABEL	0	1216
# L_56		LABEL	0	1232
# L_57		LABEL	0	1248
# L_58		LABEL	0	1296
# "i = %d\n"		STR	0	1312
# L_59		LABEL	0	1328
# L_60		LABEL	0	1344
# L_61		LABEL	0	1360
# L_62		LABEL	0	1376
# L_63		LABEL	0	1392
# L_64		LABEL	0	1408
# L_65		LABEL	0	1424
# L_66		LABEL	0	1456
# L_67		LABEL	0	1504
# L_68		LABEL	0	1520
# L_69		LABEL	0	1568
# L_70		LABEL	0	1584
# L_71		LABEL	0	1600
# L_72		LABEL	0	1616
# L_73		LABEL	0	1696
# L_74		LABEL	0	1712
# L_75		LABEL	0	1744
# L_76		LABEL	0	1760
# L_77		LABEL	0	1776
# "i = fun(n[%d]) = %d\n"		STR	0	1792
# L_78		LABEL	0	1824
# L_79		LABEL	0	1840
# L_80		LABEL	0	1856
# L_81		LABEL	0	1872
# L_82		LABEL	0	1888
#-------------------------------------
#-------------------------------------
# fun		INT	0	144
# 176		INT	0	176
# main		VOID	0	192
# 1904		INT	0	1904
#-------------------------------------
#-------------------------------------
# i		INT	0	16
#-------------------------------------
#-------------------------------------
# printf		STR	0	96
# 2		INT	0	112
#-------------------------------------
#-------------------------------------
#-------------------------------------
#-------------------------------------
# i		INT	0	16
# j		INT	0	32
# 1		INT	0	64
# 2		INT	0	96
# printf		STR	0	176
# t_0		INT	0	224
# t_1		INT	0	240
# t_2		INT	0	368
# t_3		INT	0	384
# 10		INT	0	512
# t_4		INT	0	528
# t_5		INT	0	640
# 2		INT	0	752
# t_6		INT	0	768
# t_7		INT	0	880
# 3		INT	0	992
# t_8		INT	0	1008
# t_9		INT	0	1024
# t_10		INT	0	1056
# 0		INT	0	1184
# t_11		BOOLEAN	0	1200
# 1		INT	0	1440
# 3		INT	0	1472
# t_12		INT	0	1488
# 2		INT	0	1632
# t_14		INT	0	1648
# 8		INT	0	1664
# t_15		INT	0	1680
# t_16		INT	0	1728
# 2		INT	0	1808
#-------------------------------------
#-------------------------------------
# i		INT	0	1264
# 2		INT	0	1280
#-------------------------------------
#-------------------------------------
#-------------------------------------
#-------------------------------------
# t_13		INT	0	1536
# 8		INT	0	1552
#-------------------------------------
.globl main
.data
i: .quad 0
j: .quad 0
n: .quad 0, 0, 0, 0, 0
k: .quad 0
l: .quad 10
m: .quad 1
str64: .asciz "%d\n"
str144: .asciz "i = j = %d\n"
str304: .asciz "i += j = %d\n"
str448: .asciz "i -= j = %d\n"
str576: .asciz "i = j + 10 = %d\n"
str688: .asciz "i = j - i = %d\n"
str816: .asciz "i = i * 2 = %d\n"
str928: .asciz "i = i / j = %d\n"
str1120: .asciz "i = i % 3 = %d\n"
str1312: .asciz "i = %d\n"
str1792: .asciz "i = fun(n[%d]) = %d\n"
.text

#fun:   
fun: push %rbp
mov %rsp, %rbp


#L_1: 176 frame 
L_1: sub $176, %rsp


#L_2:  push %rdi 
L_2: mov %rdi, -16(%rbp)


#L_3: "%d\n" rdi 
L_3: mov $str64, %rdi


#L_4: i rsi 
L_4: mov -16(%rbp), %rsi


#L_5:  = printf call 2
L_5: call printf


#L_6: 176 = i ret 
L_6: mov -16(%rbp), %rax
add $176, %rsp
pop %rbp
ret


#main:   
main: push %rbp
mov %rsp, %rbp


#L_8: 1904 frame 
L_8: sub $1904, %rsp


#L_9: i = 1 = 
L_9: mov $1, %rax
mov %rax, -16(%rbp)


#L_10: j = 2 = 
L_10: mov $2, %rax
mov %rax, -32(%rbp)


#L_11: i = j = 
L_11: mov -32(%rbp), %rax
mov %rax, -16(%rbp)


#L_12: "i = j = %d\n" rdi 
L_12: mov $str144, %rdi


#L_13: i rsi 
L_13: mov -16(%rbp), %rsi


#L_14:  = printf call 2
L_14: call printf


#L_15: t_0 = i = 
L_15: mov -16(%rbp), %rax
mov %rax, -224(%rbp)


#L_16: t_1 = t_0 + j
L_16: mov -224(%rbp), %rax
mov -32(%rbp), %rbx
add %rbx, %rax
mov %rax, -240(%rbp)


#L_17: i = t_1 = 
L_17: mov -240(%rbp), %rax
mov %rax, -16(%rbp)


#L_18: "i += j = %d\n" rdi 
L_18: mov $str304, %rdi


#L_19: i rsi 
L_19: mov -16(%rbp), %rsi


#L_20:  = printf call 2
L_20: call printf


#L_21: t_2 = i = 
L_21: mov -16(%rbp), %rax
mov %rax, -368(%rbp)


#L_22: t_3 = t_2 - j
L_22: mov -368(%rbp), %rax
mov -32(%rbp), %rbx
sub %rbx, %rax
mov %rax, -384(%rbp)


#L_23: i = t_3 = 
L_23: mov -384(%rbp), %rax
mov %rax, -16(%rbp)


#L_24: "i -= j = %d\n" rdi 
L_24: mov $str448, %rdi


#L_25: i rsi 
L_25: mov -16(%rbp), %rsi


#L_26:  = printf call 2
L_26: call printf


#L_27: t_4 = j + 10
L_27: mov -32(%rbp), %rax
mov $10, %rbx
add %rbx, %rax
mov %rax, -528(%rbp)


#L_28: i = t_4 = 
L_28: mov -528(%rbp), %rax
mov %rax, -16(%rbp)


#L_29: "i = j + 10 = %d\n" rdi 
L_29: mov $str576, %rdi


#L_30: i rsi 
L_30: mov -16(%rbp), %rsi


#L_31:  = printf call 2
L_31: call printf


#L_32: t_5 = j - i
L_32: mov -32(%rbp), %rax
mov -16(%rbp), %rbx
sub %rbx, %rax
mov %rax, -640(%rbp)


#L_33: i = t_5 = 
L_33: mov -640(%rbp), %rax
mov %rax, -16(%rbp)


#L_34: "i = j - i = %d\n" rdi 
L_34: mov $str688, %rdi


#L_35: i rsi 
L_35: mov -16(%rbp), %rsi


#L_36:  = printf call 2
L_36: call printf


#L_37: t_6 = i * 2
L_37: mov -16(%rbp), %rax
mov $2, %rbx
mulq %rbx
mov %rax, -768(%rbp)


#L_38: i = t_6 = 
L_38: mov -768(%rbp), %rax
mov %rax, -16(%rbp)


#L_39: "i = i * 2 = %d\n" rdi 
L_39: mov $str816, %rdi


#L_40: i rsi 
L_40: mov -16(%rbp), %rsi


#L_41:  = printf call 2
L_41: call printf


#L_42: t_7 = i / j
L_42: mov $0, %rdx
mov -16(%rbp), %rax
mov -32(%rbp), %rbx
idiv %rbx
mov %rax, -880(%rbp)


#L_43: i = t_7 = 
L_43: mov -880(%rbp), %rax
mov %rax, -16(%rbp)


#L_44: "i = i / j = %d\n" rdi 
L_44: mov $str928, %rdi


#L_45: i rsi 
L_45: mov -16(%rbp), %rsi


#L_46:  = printf call 2
L_46: call printf


#L_47: t_8 = i / 3
L_47: mov $0, %rdx
mov -16(%rbp), %rax
mov $3, %rbx
idiv %rbx
mov %rax, -1008(%rbp)


#L_48: t_9 = t_8 * 3
L_48: mov -1008(%rbp), %rax
mov $3, %rbx
mulq %rbx
mov %rax, -1024(%rbp)


#L_49: t_10 = i - t_9
L_49: mov -16(%rbp), %rax
mov -1024(%rbp), %rbx
sub %rbx, %rax
mov %rax, -1056(%rbp)


#L_50: i = t_10 = 
L_50: mov -1056(%rbp), %rax
mov %rax, -16(%rbp)


#L_51: "i = i % 3 = %d\n" rdi 
L_51: mov $str1120, %rdi


#L_52: i rsi 
L_52: mov -16(%rbp), %rsi


#L_53:  = printf call 2
L_53: call printf


#L_54: t_11 = i cmp 0
L_54: mov -16(%rbp), %rax
mov $0, %rbx
cmp %rax, %rbx
mov %rax, -1200(%rbp)


#L_55: L_57 = t_11 jl 
L_55: jl L_57


#L_56: L_62 = t_11 jge 
L_56: jge L_62


#L_57: i = 2 = 
L_57: mov $2, %rax
mov %rax, -1264(%rbp)


#L_58: "i = %d\n" rdi 
L_58: mov $str1312, %rdi


#L_59: i rsi 
L_59: mov -1264(%rbp), %rsi


#L_60:  = printf call 2
L_60: call printf


#L_61: L_65 goto 
L_61: jmp L_65


#L_62: "i = %d\n" rdi 
L_62: mov $str1312, %rdi


#L_63: i rsi 
L_63: mov -16(%rbp), %rsi


#L_64:  = printf call 2
L_64: call printf


#L_65: i = 1 = 
L_65: mov $1, %rax
mov %rax, -16(%rbp)


#L_66: t_12 = i cmp 3
L_66: mov -16(%rbp), %rax
mov $3, %rbx
cmp %rax, %rbx
mov %rax, -1488(%rbp)


#L_67: L_72 = t_12 jle 
L_67: jle L_72


#L_68: t_13 = i * 8
L_68: mov -16(%rbp), %rax
mov $8, %rbx
mulq %rbx
mov %rax, -1536(%rbp)


#L_69: n = t_13 []= i
L_69: mov $n, %rax
mov -1536(%rbp), %rbx
add %rbx, %rax
mov -16(%rbp), %rbx
mov %rbx, (%rax)


#L_70: i = i + 1
L_70: mov -16(%rbp), %rax
mov $1, %rbx
add %rbx, %rax
mov %rax, -16(%rbp)


#L_71: L_66 goto 
L_71: jmp L_66


#L_72: t_14 = 2 * 8
L_72: mov $2, %rax
mov $8, %rbx
mulq %rbx
mov %rax, -1648(%rbp)


#L_73: t_15 = n [] t_14
L_73: mov $n, %rax
mov -1648(%rbp), %rbx
add %rbx, %rax
mov (%rax), %rbx
mov %rbx, -1680(%rbp)


#L_74: t_15 rdi 
L_74: mov -1680(%rbp), %rdi


#L_75: t_16 = fun callexp 1
L_75: call fun
mov %rax, -1728(%rbp)


#L_76: i = t_16 = 
L_76: mov -1728(%rbp), %rax
mov %rax, -16(%rbp)


#L_77: "i = fun(n[%d]) = %d\n" rdi 
L_77: mov $str1792, %rdi


#L_78: 2 rsi 
L_78: mov $2, %rsi


#L_79: i rdx 
L_79: mov -16(%rbp), %rdx


#L_80:  = printf call 3
L_80: call printf


#L_81: 1904 ret 
L_81: add $1904, %rsp
pop %rbp
ret

