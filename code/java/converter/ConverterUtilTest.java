package project.name.converter; //NOSONAR

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConverterUtilTest {

    @Test
    void removeLineBreaks_should_remove_line_breaks_from_the_text() {

        String text =
            "{\n" + "  \"input\": {\n" + "    \"text\": \"qual é o melhor dia de pagamentos\"\n" + "  },\n" + "  \"intents\": [\n"
                + "    {\n" + "      \"intent\": \"SkillPerson\",\n" + "      \"confidence\": 0.9975897789001464\n" + "    }\n" + "  ],\n"
                + "  \"entities\": [\n" + "    {\n" + "      \"entity\": \"SkillEspecialista\",\n" + "      \"location\": [\n"
                + "        33,\n" + "        43\n" + "      ],\n" + "      \"value\": \"Skill\",\n" + "      \"confidence\": 1.0\n"
                + "    }\n" + "  ],\n" + "  \"context\": {\n" + "    \"conversation_id\": \"0cd58fc4-53f7-4c9b-84b0-9830ef99d80d\",\n"
                + "    \"system\": {\n" + "      \"initialized\": true,\n" + "      \"dialog_stack\": [\n" + "        {\n"
                + "          \"dialog_node\": \"root\"\n" + "        }\n" + "      ],\n" + "      \"dialog_turn_counter\": 1.0,\n"
                + "      \"dialog_request_counter\": 1.0,\n" + "      \"_node_output_map\": {\n" + "        \"node_9_1565724339097\": {\n"
                + "          \"0\": [\n" + "            0.0\n" + "          ]\n" + "        }\n" + "      },\n"
                + "      \"last_branch_node\": \"node_10_1574857211759\",\n" + "      \"branch_exited\": true,\n"
                + "      \"branch_exited_reason\": \"completed\"\n" + "    },\n" + "    \"metadata\": {\n"
                + "      \"user_id\": \"0cd58fc4-53f7-4c9b-84b0-9830ef99d80d\"\n" + "    },\n" + "    \"erroAoBuscarCpfIntercom\": false,\n"
                + "    \"Email\": \"homologacao@email.com\",\n" + "    \"SOCIAL_ID\": \"0011100\",\n"
                + "    \"AssuntoConversa\": \"Roteamento para Skill\",\n" + "    \"idConversa\": \"0000111\",\n"
                + "    \"contadorNaoEntendeu\": 0,\n" + "    \"Visitante\": \"false\",\n" + "    \"regra\": true,\n"
                + "    \"TipoUsuario\": \"LOGADO\",\n" + "    \"dataHoje\": \"2021-07-26\",\n"
                + "    \"texto_usuario\": \"qual é o melhor dia de pagamentos\",\n" + "    \"origemIntercom\": \"IB\",\n"
                + "    \"IdUsuario\": null,\n" + "    \"skillAtual\": \"ORQUESTRADORA\",\n" + "    \"Feriados\": [\n"
                + "      \"2021-06-03\",\n" + "      \"2021-08-15\",\n" + "      \"2021-09-07\",\n" + "      \"2021-10-12\",\n"
                + "      \"2021-11-02\",\n" + "      \"2021-11-15\",\n" + "      \"2021-12-08\",\n" + "      \"2021-12-25\",\n"
                + "      \"2022-01-01\"\n" + "    ],\n" + "    \"Nome\": \"BOT\",\n"
                + "    \"confiancaIntencao\": 0.9975897789001464,\n" + "    \"skillAnterior\": \"ORQUESTRADORA\",\n"
                + "    \"skill_destino\": \"Person\",\n" + "    \"TiposDeConta\": \"PF\",\n"
                + "    \"intencaoResposta\": \"SkillPerson\"\n" + "  },\n" + "  \"output\": {\n" + "    \"nodes_visited\": [\n"
                + "      \"node_1_1561397881361\",\n" + "      \"node_9_1565724339097\"\n" + "    ],\n" + "    \"log_messages\": [],\n"
                + "    \"text\": [\n" + "      \"{{TROCAR_SKILL}}\"\n" + "    ],\n" + "    \"generic\": [\n" + "      {\n"
                + "        \"response_type\": \"text\",\n" + "        \"text\": \"{{TROCAR_SKILL}}\"\n" + "      }\n" + "    ],\n"
                + "    \"deleted_variable\": \"deleted_variable\"\n" + "  }\n" + "}";

        String expectedText = "{\n\"input\": {\n\"text\": \"qual é o melhor dia de pagamentos\"\n},\n\"intents\": [\n{\n\"intent\": \"SkillPerson\",\n\"confidence\": 0.9975897789001464\n}\n],\n\"entities\": [\n{\n\"entity\": \"SkillEspecialista\",\n\"location\": [\n33,\n43\n],\n\"value\": \"Skill\",\n\"confidence\": 1.0\n}\n],\n\"context\": {\n\"conversation_id\": \"0cd58fc4-53f7-4c9b-84b0-9830ef99d80d\",\n\"system\": {\n\"initialized\": true,\n\"dialog_stack\": [\n{\n\"dialog_node\": \"root\"\n}\n],\n\"dialog_turn_counter\": 1.0,\n\"dialog_request_counter\": 1.0,\n\"_node_output_map\": {\n\"node_9_1565724339097\": {\n\"0\": [\n0.0\n]\n}\n},\n\"last_branch_node\": \"node_10_1574857211759\",\n\"branch_exited\": true,\n\"branch_exited_reason\": \"completed\"\n},\n\"metadata\": {\n\"user_id\": \"0cd58fc4-53f7-4c9b-84b0-9830ef99d80d\"\n},\n\"erroAoBuscarCpfIntercom\": false,\n\"Email\": \"homologacao@email.com\",\n\"SOCIAL_ID\": \"0011100\",\n\"AssuntoConversa\": \"Roteamento para Skill\",\n\"idConversa\": \"0000111\",\n\"contadorNaoEntendeu\": 0,\n\"Visitante\": \"false\",\n\"regra\": true,\n\"TipoUsuario\": \"LOGADO\",\n\"dataHoje\": \"2021-07-26\",\n\"texto_usuario\": \"qual é o melhor dia de pagamentos\",\n\"origemIntercom\": \"IB\",\n\"IdUsuario\": null,\n\"skillAtual\": \"ORQUESTRADORA\",\n\"Feriados\": [\n\"2021-06-03\",\n\"2021-08-15\",\n\"2021-09-07\",\n\"2021-10-12\",\n\"2021-11-02\",\n\"2021-11-15\",\n\"2021-12-08\",\n\"2021-12-25\",\n\"2022-01-01\"\n],\n\"Nome\": \"BOT\",\n\"confiancaIntencao\": 0.9975897789001464,\n\"skillAnterior\": \"ORQUESTRADORA\",\n\"skill_destino\": \"Person\",\n\"TiposDeConta\": \"PF\",\n\"intencaoResposta\": \"SkillPerson\"\n},\n\"output\": {\n\"nodes_visited\": [\n\"node_1_1561397881361\",\n\"node_9_1565724339097\"\n],\n\"log_messages\": [],\n\"text\": [\n\"{{TROCAR_SKILL}}\"\n],\n\"generic\": [\n{\n\"response_type\": \"text\",\n\"text\": \"{{TROCAR_SKILL}}\"\n}\n],\n\"deleted_variable\": \"deleted_variable\"\n}\n}";

        Optional<String> returned = ConverterUtil.toJsonStringNoBeautiful(text);

        Assertions.assertTrue(returned.isPresent());
    }

    @Test
    void removeLineBreaks_should_return_the_same_string_if_it_is_null_or_empty() {

        String textNull = null;
        String textEmptyNull = " ";

        Assertions.assertFalse(ConverterUtil.toJsonStringNoBeautiful(textNull).isPresent());
        Assertions.assertFalse(ConverterUtil.toJsonStringNoBeautiful(textEmptyNull).isPresent());
    }
}
