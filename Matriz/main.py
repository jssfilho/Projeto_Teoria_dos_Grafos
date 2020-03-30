from grafo import *
from matriz_g import *
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

m=Matriz(g)
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
    print("8 - Caminho Euleriano(O grafo será considerado NÃO DIRECIONADO)")
    print("9 - Matriz Warshall")
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
        for i in m.nao_adjacentes():
            print(i+', ', end=" ")
    elif num==2:
        print("Se existem verteces adjacentes a ele mesmo?")
        if  (m.adjacente_nele()):
            print("SIM")
        else:
            print("NÃO")
    elif num == 3:
        print("Se há arestas paralelas: ")
        if  (m.se_paralelas()):
            print("SIM")
        else:
            print("NÃO")
    elif num == 4:
        while True:
            try:
                n=input("Informe um vertece para saber o grau do mesmo: ")
                if not m.G.existeVertice(n):
                    raise VerticeInvalidoException
                break
            except VerticeInvalidoException:
                print("Digite um vertice valido")

        print("O grau de entrada do vertice é",m.grau_vertice(n)[1])
        print("O grau de saida do vertice é ",m.grau_vertice(n)[0])
    elif num == 5:
        while True:
            try:
                n=input("Informe um vertece para saber quais arestas incidem sobre ele: ")
                if not m.G.existeVertice(n):
                    raise VerticeInvalidoException
                break
            except VerticeInvalidoException:
                print("Digite um vertice valido")

        print("A arestas são: ")
        vertices_incidentes=m.incidem_vertice(n)
        for i in vertices_incidentes:
            print(i+', ', end=" ")
    elif num == 6:
        print(" O grafo é completo?")
        if  (m.se_completo()):
            print("SIM")
        else:
            print("NÃO")
    elif num == 7:
        print("(Desafio) O grafo é conexo?")
        if  (m.se_conexo()):
            print("SIM")
        else:
            print("NÃO")
    elif num == 0:
        print("Tchau")
        break
    elif num == 8:
        print("Existe caminho Euleriano?")
        if  (m.existe_caminhoEuleriano()):
            print("SIM")
        else:
            print("NÃO")
    elif num == 9:
        m.algWarshall()
    else:
        print("Numero invalido")
        print("\n")

#os testes para grafo direcionado ainda não estão prontos
# testes
# J, C, E, P, M, T, Z
# a1(J-C), a2(E-C), a3(C-E), a4(C-P), a5(C-C), a6(C-M), a7(C-T), a8(M-T), a9(Z-Z)
#a1(J-C), a2(E-C), a3(C-E), a4(C-P), a5(C-C), a6(C-M), a7(C-T), a8(M-T), a9(T-Z)
# J, C, B
# a1(J-C), a2(J-B), a3(C-B)
# a1(J-C), a2(J-B), a3(C-B), a4(J-J), a5(B-B), a6(C-C)
# a1(J-C), a2(J-B), a3(C-B), a4(C-J), a5(B-J), a6(B-C)
#a7(J-J), a8(B-B), a9(C-C), a1(J-C), a2(J-B), a3(C-B), a4(C-J), a5(B-J), a6(B-C)
