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
    usecase "Matricular em disciplina" as UC_Matricular
    usecase "Cancelar matrícula" as UC_Cancelar
    usecase "Matricular em disciplina\noptativa" as UC_Optativa
    usecase "Matricular em disciplina\nobrigatória" as UC_Obrigatoria

    ' --- CASOS DE USO PROFESSOR ---
    usecase "Consultar turmas atribuídas" as UC_TurmasProfessor

    ' --- CASOS DE USO SECRETARIA ---
    usecase "Abrir período de matrícula" as UC_AbrirPeriodo
    usecase "Encerrar período de matrícula" as UC_EncerrarPeriodo
    usecase "Cadastrar usuário" as UC_CadastrarUsuario
    usecase "Consultar usuário" as UC_ConsultarUsuario
    usecase "Remover usuário" as UC_RemoverUsuario
    usecase "Atualizar usuário" as UC_AtualizarUsuario
    usecase "Definir currículo" as UC_DefinirCurriculo
    usecase "Adicionar disciplina" as UC_Adicionar_Disciplina
    usecase "Remover disciplina" as UC_Remover_Disciplina
    usecase "Definir disciplina pré-requisito" as UC_PreRequisito
    usecase "Definir disciplina co-requisito" as UC_CoRequisito


    ' --- CASOS DE USO DE SUPORTE ---
    usecase "Notificar Sistema de Cobranças" as UC_NotificarCobranca
    usecase "Login no sistema" as UC_Login
    usecase "Recuperar senha" as UC_RecuperarSenha
}

' ===================================
' RELAÇÕES
' ===================================

' --- Ação comum a todos os usuários ---
Usuario -- UC_Login

' --- Relações de Aluno ---
Aluno -- UC_Matricular
UC_Matricular ..> UC_Obrigatoria : <<include>>
UC_Cancelar <.. UC_Matricular : <<extend>>
UC_Optativa <.. UC_Matricular : <<extend>>
UC_Matricular ..> UC_NotificarCobranca : <<include>>


' --- Relações de Professor ---
Professor -- UC_TurmasProfessor

' --- Relações da Secretaria ---
Secretaria -- UC_AbrirPeriodo
Secretaria -- UC_EncerrarPeriodo
Secretaria -- UC_CadastrarUsuario
Secretaria -- UC_ConsultarUsuario
Secretaria -- UC_RemoverUsuario
Secretaria -- UC_AtualizarUsuario
Secretaria -- UC_DefinirCurriculo

' Relações de Currículo (CORRIGIDO)
UC_DefinirCurriculo ..> UC_Adicionar_Disciplina : <<include>>
UC_DefinirCurriculo ..> UC_Remover_Disciplina : <<include>>

UC_PreRequisito <.. UC_Adicionar_Disciplina : <<extend>>
UC_CoRequisito <.. UC_Adicionar_Disciplina : <<extend>>


' --- Extensões condicionais ---
UC_RecuperarSenha <.. UC_Login : <<extend>>

' --- Interação com sistema externo ---
UC_NotificarCobranca -- SistemaCobrancas
@enduml
