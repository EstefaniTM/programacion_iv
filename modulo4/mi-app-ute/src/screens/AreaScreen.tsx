import { useMemo, useState } from "react";
import {
  Alert,
  KeyboardAvoidingView,
  Platform,
  Pressable,
  StyleSheet,
  Text,
  TextInput,
  View,
} from "react-native";

type AreaForm = {
  base: number;
  height: number;
};

type AreaErrors = Partial<Record<keyof AreaForm, string>>;

export function AreaScreen() {
  const [form, setForm] = useState<AreaForm>({ base: 0, height: 0 });
  const [errors, setErrors] = useState<AreaErrors>({});
  const [submitted, setSubmitted] = useState<boolean>(false);

  const bg = "#0d1117";
  const card = "#161b22";
  const border = "#30363d";
  const text = "#c9d1d9";
  const primary = "#58a6ff";
  const danger = "#f85149";

  function validate(next: AreaForm): AreaErrors {
    const e: AreaErrors = {};

    if (next.base <= 0) {
      e.base = "Base debe ser mayor a 0";
    }
    if (next.height <= 0) {
      e.height = "Altura debe ser mayor a 0";
    }

    return e;
  }

  const isOk = useMemo(() => {
    const e = validate(form);
    return Object.keys(e).length === 0;
  }, [form]);

  const area = useMemo(() => {
    if (isOk) return form.base * form.height;
    return 0;
  }, [form, isOk]);

  function onChange<K extends keyof AreaForm>(key: K, value: string) {
    const numValue = parseFloat(value) || 0;
    setForm((prev) => ({ ...prev, [key]: numValue }));
    if (submitted) {
      setErrors(validate({ ...form, [key]: numValue } as AreaForm));
    }
  }

  function clear() {
    setForm({ base: 0, height: 0 });
    setErrors({});
    setSubmitted(false);
  }

  function submit() {
    setSubmitted(true);
    const e = validate(form);
    setErrors(e);

    if (Object.keys(e).length > 0) return;

    Alert.alert("✅ Cálculo completado", `Área = ${area} unidades²`);
  }

  return (
    <KeyboardAvoidingView
      style={{ flex: 1, backgroundColor: bg }}
      behavior={Platform.OS === "ios" ? "padding" : undefined}
    >
      <View style={styles.container}>
        <Text style={[styles.title, { color: primary }]}>Calcular Área</Text>

        <View style={[styles.card, { backgroundColor: card, borderColor: border }]}>
          <Text style={[styles.label, { color: text }]}>Base</Text>
          <TextInput
            value={form.base.toString()}
            onChangeText={(t) => onChange("base", t)}
            placeholder="ej: 5"
            placeholderTextColor="#8b949e"
            keyboardType="decimal-pad"
            style={[
              styles.input,
              { backgroundColor: card, borderColor: errors.base ? danger : border, color: text },
            ]}
          />
          {errors.base ? (
            <Text style={[styles.error, { color: danger }]}>{errors.base}</Text>
          ) : null}

          <Text style={[styles.label, { color: text, marginTop: 10 }]}>Altura</Text>
          <TextInput
            value={form.height.toString()}
            onChangeText={(t) => onChange("height", t)}
            placeholder="ej: 3"
            placeholderTextColor="#8b949e"
            keyboardType="decimal-pad"
            style={[
              styles.input,
              { backgroundColor: card, borderColor: errors.height ? danger : border, color: text },
            ]}
          />
          {errors.height ? (
            <Text style={[styles.error, { color: danger }]}>{errors.height}</Text>
          ) : null}

          {isOk && (
            <Text style={[styles.result, { color: primary }]}>
              Área = {area} unidades²
            </Text>
          )}

          <Pressable
            style={[
              styles.btn,
              { borderColor: primary, opacity: isOk ? 1 : 0.6 },
            ]}
            onPress={submit}
          >
            <Text style={[styles.btnText, { color: primary }]}>Calcular</Text>
          </Pressable>

          <Pressable style={[styles.btnGhost, { borderColor: border }]} onPress={clear}>
            <Text style={[styles.btnText, { color: text }]}>Limpiar</Text>
          </Pressable>

          <Text style={[styles.hint, { color: text }]}>
            Tip: Ingresa números mayores a 0 en ambos campos.
          </Text>
        </View>
      </View>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    justifyContent: "center",
  },
  title: {
    fontWeight: "900",
    fontSize: 22,
    marginBottom: 10,
  },
  card: {
    borderWidth: 1,
    borderRadius: 12,
    padding: 14,
  },
  label: {
    fontWeight: "800",
    opacity: 0.9,
    marginBottom: 6,
  },
  input: {
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 10,
    paddingHorizontal: 12,
  },
  error: {
    marginTop: 6,
    fontWeight: "800",
  },
  result: {
    marginTop: 12,
    fontWeight: "900",
    fontSize: 18,
  },
  btn: {
    marginTop: 14,
    backgroundColor: "#21262d",
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 10,
    paddingHorizontal: 14,
    alignItems: "center",
  },
  btnGhost: {
    marginTop: 10,
    backgroundColor: "transparent",
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 10,
    paddingHorizontal: 14,
    alignItems: "center",
  },
  btnText: {
    fontWeight: "900",
  },
  hint: {
    marginTop: 12,
    opacity: 0.85,
    lineHeight: 20,
  },
});