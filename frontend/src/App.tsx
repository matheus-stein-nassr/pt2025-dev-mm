import React, { useState } from "react";
import { useForm } from "react-hook-form";
import type { SubmitHandler } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import styled from "styled-components";
import * as yup from "yup";

import Input from "./components/Input";
import Button from "./components/Button";
import Card from "./components/Card";
import Section from "./components/Section";
import LogoBandeira from "./components/LogoBandeira";

import { maskCardNumber, maskCPF, maskCVV, maskDate } from "./utils/masks";
import { getCardBrand } from "./utils/cardBrand";
import api from "./services/api";

interface FormData {
  nomeCompleto: string;
  cpf: string;
  dataNascimento: string;
  numeroCartao: string;
  nomeImpresso: string;
  validade: string;
  cvv: string;
  email: string;
}

const schema = yup.object({
  nomeCompleto: yup.string().required("Nome completo obrigatório"),
  cpf: yup
    .string()
    .required("CPF obrigatório")
    .matches(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/, "CPF inválido"),
  dataNascimento: yup
    .string()
    .required("Data de nascimento obrigatória")
    .test("maior-de-idade", "Você deve ser maior de idade", (value) => {
      if (!value) return false;

      const nascimento = new Date(value);
      const hoje = new Date();

      // Calcula a idade em anos
      let idade = hoje.getFullYear() - nascimento.getFullYear();

      // Ajusta se o aniversário ainda não passou no ano atual
      const mesPassou =
        hoje.getMonth() > nascimento.getMonth() ||
        (hoje.getMonth() === nascimento.getMonth() &&
          hoje.getDate() >= nascimento.getDate());
      if (!mesPassou) idade--;

      return idade >= 18;
    }),
  numeroCartao: yup
    .string()
    .required("Número do cartão obrigatório")
    .min(19, "Número incompleto"),
  nomeImpresso: yup.string().required("Nome impresso obrigatório"),
  validade: yup
    .string()
    .required("Validade obrigatória")
    .matches(/^(0[1-9]|1[0-2])\/?([0-9]{2})$/, "Formato MM/AA inválido")
    .test("valida-cartao-vencido", "Cartão vencido", (value) => {
      if (!value) return false;

      const [mm, aa] = value.split("/");
      const validadeDate = new Date(+`20${aa}`, +mm - 1); // 1º dia do mês seguinte
      const hoje = new Date();

      // Compara só o mês e ano
      return validadeDate >= new Date(hoje.getFullYear(), hoje.getMonth());
    }),
  cvv: yup
    .string()
    .required("CVV obrigatório")
    .length(3, "CVV deve ter 3 dígitos"),
  email: yup.string().required("Email obrigatório").email("Email inválido"),
});

const Container = styled.main`
  min-height: 100vh;
  padding: 2rem 1rem;
  background: #f1f1f1;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: Arial, sans-serif;
`;

export default function App() {
  const [brand, setBrand] = useState<"visa" | "mastercard" | null>(null);

  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors, isValid },
    reset,
  } = useForm<FormData>({
    resolver: yupResolver(schema),
    mode: "onChange",
    defaultValues: {
      nomeCompleto: "",
      cpf: "",
      dataNascimento: "",
      numeroCartao: "",
      nomeImpresso: "",
      validade: "",
      cvv: "",
      email: "",
    },
  });

  const detectBrand = (number: string) => {
    const brand = getCardBrand(number);
    setBrand(brand);
  };

  const handleCardNumberChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const masked = maskCardNumber(e.target.value);
    setValue("numeroCartao", masked, { shouldValidate: true });
    detectBrand(masked);
  };

  const handleExpirationChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const masked = maskDate(e.target.value);
    setValue("validade", masked, { shouldValidate: true });
  };

  const handleCPFChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const masked = maskCPF(e.target.value);
    setValue("cpf", masked, { shouldValidate: true });
  };

  const handleCVVChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const masked = maskCVV(e.target.value);
    setValue("cvv", masked, { shouldValidate: true });
  };

  const onSubmit: SubmitHandler<FormData> = async (data) => {
    const payload = {
      nomeCompleto: data.nomeCompleto,
      cpf: data.cpf,
      dataNascimento: data.dataNascimento,
      numeroCartao: data.numeroCartao,
      nomeImpresso: data.nomeImpresso,
      validade: data.validade,
      cvv: data.cvv,
      email: data.email,
    };

    console.log("Enviando para o backend:", payload);

    try {
      await api.post("/api/payments", payload);
      alert("✅ Pagamento realizado com sucesso!");
    } catch (error) {
      console.error("Erro ao enviar para o backend:", error);
      alert("❌ Erro no processamento do pagamento.");
    }
  };

  return (
    <Container>
      <Card>
        <form onSubmit={handleSubmit(onSubmit)} noValidate>
          <Section>
            <h2>Informações Pessoais</h2>

            <Input
              label="Nome Completo"
              {...register("nomeCompleto")}
              error={errors.nomeCompleto?.message}
            />

            <Input
              label="CPF"
              {...register("cpf")}
              onChange={handleCPFChange}
              maxLength={14}
              error={errors.cpf?.message}
            />

            <Input
              label="Data de Nascimento"
              {...register("dataNascimento")}
              type="date"
              error={errors.dataNascimento?.message}
            />

            <Input
              label="Email"
              {...register("email")}
              type="email"
              error={errors.email?.message}
            />
          </Section>

          <Section>
            <h2>Dados do Cartão</h2>

            <Input
              label="Número do Cartão"
              {...register("numeroCartao")}
              onChange={handleCardNumberChange}
              maxLength={19}
              error={errors.numeroCartao?.message}
            />

            <LogoBandeira brand={brand} />

            <Input
              label="Nome Impresso no Cartão"
              {...register("nomeImpresso")}
              error={errors.nomeImpresso?.message}
            />

            <Input
              label="Validade (MM/AA)"
              {...register("validade")}
              onChange={handleExpirationChange}
              maxLength={5}
              error={errors.validade?.message}
            />

            <Input
              label="CVV"
              {...register("cvv")}
              onChange={handleCVVChange}
              maxLength={3}
              error={errors.cvv?.message}
            />
          </Section>

          <Button
            type="button"
            onClick={() => reset()}
            style={{ backgroundColor: "indianred", marginBottom: "10px" }}
          >
            Limpar
          </Button>

          <Button type="submit" disabled={!isValid}>
            Processar
          </Button>
        </form>
      </Card>
    </Container>
  );
}
