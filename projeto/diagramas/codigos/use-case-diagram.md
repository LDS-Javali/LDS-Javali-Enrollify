@startuml
' Configurações de aparência
skinparam actorStyle awesome
skinparam usecase {
    BackgroundColor #LightSkyBlue
    BorderColor #181818
}
skinparam rectangle {
    BackgroundColor #WhiteSmoke
    BorderColor #181818
}
left to right direction

' ===================================
' ATORES E GENERALIZAÇÃO
' ===================================
actor "Usuário" as Usuario
actor Aluno
actor Professor
actor Secretaria
actor "Sistema de Cobranças" as SistemaCobrancas <<system>>

' Generalização: Todos os perfis são um tipo de Usuário
Usuario <|-- Aluno
Usuario <|-- Professor
Usuario <|-- Secretaria

' ===================================
' PACOTE DO SISTEMA
' ===================================
rectangle "Sistema de Matrículas" {

    ' --- CASOS DE USO ALUNO ---
    usecase "Consultar disponibilidade\nde disciplinas" as UC_ConsultarVagas
    usecase "Realizar matrícula" as UC_Realizar
    usecase "Cancelar matrícula" as UC_Cancelar
    usecase "Consultar minhas matrículas" as UC_MinhasMatriculas
    usecase "Consultar Status final\nda Disciplina" as UC_StatusDisciplina
    usecase "Consultar cobranças" as UC_ConsultarCobranca

    ' --- CASOS DE USO PROFESSOR ---
    usecase "Consultar alunos matriculados" as UC_AlunosMatriculados
    usecase "Consultar turmas atribuídas" as UC_TurmasProfessor

    ' --- CASOS DE USO SECRETARIA ---
    usecase "Gerenciar cadastros" as UC_GerenciarCadastros
    usecase "Gerenciar o currículo do semestre" as UC_GerenciarCurriculo
    usecase "Definir período de matrícula" as UC_DefinirPeriodo
    usecase "Processar encerramento\ndo período de matrícula" as UC_ProcessarFim
    usecase "Notificar Aluno sobre\nStatus da Disciplina" as UC_NotificarAluno
    usecase "Alocar professor à turma" as UC_AlocarProfessor

    ' --- CASOS DE USO DE SUPORTE ---
    usecase "Notificar Sistema de Cobranças" as UC_NotificarCobranca
    usecase "Login no sistema" as UC_Login
}

' ===================================
' RELAÇÕES
' ===================================

' --- Ação comum a todos os usuários ---
Usuario -- UC_Login

' --- Relações de Aluno ---
Aluno -- UC_ConsultarVagas
Aluno -- UC_Realizar
Aluno -- UC_Cancelar
Aluno -- UC_MinhasMatriculas
Aluno -- UC_StatusDisciplina
Aluno -- UC_ConsultarCobranca

' --- Relações de Professor ---
Professor -- UC_AlunosMatriculados
Professor -- UC_TurmasProfessor
Professor -- UC_StatusDisciplina

' --- Relações de Secretaria ---
Secretaria -- UC_GerenciarCadastros
Secretaria -- UC_GerenciarCurriculo
Secretaria -- UC_DefinirPeriodo
Secretaria -- UC_ProcessarFim
Secretaria -- UC_AlocarProfessor

' --- Inclusões obrigatórias ---
UC_Realizar ..> UC_NotificarCobranca : <<include>>

' --- Extensões condicionais ---
UC_ProcessarFim <.. UC_NotificarAluno : <<extend>>

' --- Dependência entre secretaria e professor ---
UC_AlocarProfessor ..> UC_TurmasProfessor : <<include>>

' --- Interação com sistema externo ---
UC_NotificarCobranca -- SistemaCobrancas
@enduml
