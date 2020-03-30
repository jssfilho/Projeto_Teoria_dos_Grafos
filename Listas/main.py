from grafo import *
from funcoes_q3 import *
g = Grafo()
#receiving vertex
while True:
    try:
        print("Digite as vertices separadas por virgula e espaço")
        N = input().split(', ')
        for a in range(len(N)):
            if N.count(N[a])>1 or ' ' in N[a] or ',' in N[a] or not g.verticeValido(N[a]):
                raise VerticeInvalidoException
        break
    except VerticeInvalidoException:
        print("Digite seguindo o padrão")
        print("\n")
        print("Não pode conter (','), (' '), ('-') nem nos repetidos")

for y in range(len(N)):
    g.adicionaVertice(N[y])

#receiving edges
while True:
    try:
        print("Digite as arestas separadas por virgula e espaço no seguinte formato: \n'nome(vertice1-vertice2)'")
        A = input().split(", ")
        A_tratado=[]
        for c in range(len(A)):
            A_tratado.append(A[c].split('('))
            A_tratado[-1][1] = A_tratado[-1][1].replace(')', '')

        for a in range(len(A)):
            count=0
            for b in range(len(A)):
                if A_tratado[a][0]==A_tratado[b][0]:
                    count+=1
            if not g.arestaValida(A_tratado[a][1]) or count >1:
                raise ArestaInvalidaException
        break
    except ArestaInvalidaException:
        print("Digite as arestas conforme o modelo exemploAresta(exemploNo-exemploNo2)\n")

for i in range(len(A)):
    g.adicionaAresta(A_tratado[i][0],A_tratado[i][1])
u=Funcoes(g)
while True:
    print("\n")
    print("Digite um numero referente a questão desejada!")
    print("\n")
    print("1 - Letra A")
    print("2 - Letra B")
    print("3 - Letra C")
    print("4 - Letra D")
    print("5 - Letra E")
    print("6 - Letra F")
    print("7 - (Desafio) Letra I")
    print("0 - Sair")
    while True:
        try:
            num=int(input())
            break
        except ValueError:
            print("Digite um valor valido")
            print("\n")
    if num==1:
        print("Lista de arestas não adjacentes: ")
        print(u.nao_adjacente())
    elif num==2:
        print("Se existem verteces adjacentes a ele mesmo?")
        print(u.himself())
    elif num == 3:
        print("Se há arestas paralelas: ")
        print(u.a_paralelos())
    elif num == 4:
        while True:
            try:
                n=input("Informe um vertece para saber o grau do mesmo: ")
                if not u.G.existeVertice(n):
                    raise VerticeInvalidoException
                break
            except VerticeInvalidoException:
                print("Digite um vertice valido")

        print("O grau do vertice é",u.grau_vertice(n))
    elif num == 5:
        while True:
            try:
                n=input("Informe um vertece para saber quais arestas incidem sobre ele: ")
                if not u.G.existeVertice(n):
                    raise VerticeInvalidoException
                break
            except VerticeInvalidoException:
                print("Digite um vertice valido")

        print("A arestas são: ",u.mostrar_arestas(n))
    elif num == 6:
        print(" O grafo é completo?")
        print(u.verifica_completo())
    elif num == 7:
        print("(Desafio) O grafo é conexo?")

        print(u.verifica_conexo())
    elif num == 0:
        print("Tchau")
        break
    else:
        print("Numero invalido")
        print("\n")


# testes
# J, C, E, P, M, T, Z
# a1(J-C), a2(E-C), a3(C-E), a4(C-P), a5(C-C), a6(C-M), a7(C-T), a8(M-T), a9(T-Z)
# J, C, B
# a1(J-C), a2(J-B), a3(C-B)
# a1(J-C), a2(J-B), a3(C-B), a4(J-J), a5(B-B), a6(C-C)
# a1(Andson-Manu), a2(Manu-Gabriel), a3(Manu-Gabriel), a4(Manu-Hevlla), a5(Manu-Hevlla), a6(Manu-João), a7(Manu-Leticia), a8(João-Leticia), a9(Leticia-Rennan)
# Andson, Manu, Gabriel, Hevlla, João, Leticia, Rennan
