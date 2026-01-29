import { useState } from "react";
import { StyleSheet, Text, TextInput, View } from "react-native";

type LoginForm = {
  email: string;
  password: string;
};

export function LoginFormDemo() {
  const [form, setForm] = useState<LoginForm>({ email: "", password: "" });

  return (
    <View style={styles.container}>
      <Text style={styles.label}>Email</Text>
      <TextInput
        value={form.email}
        onChangeText={(text) => setForm((prev) => ({ ...prev, email: text }))}
        style={styles.input}
        autoCapitalize="none"
        keyboardType="email-address"
        placeholder="correo@ejemplo.com"
        placeholderTextColor="#8b949e"
      />

      <Text style={styles.label}>Password</Text>
      <TextInput
        value={form.password}
        onChangeText={(text) =>
          setForm((prev) => ({ ...prev, password: text }))
        }
        style={styles.input}
        secureTextEntry
        placeholder="********"
        placeholderTextColor="#8b949e"
      />

      <View style={styles.preview}>
        <Text style={styles.previewTitle}>Preview (estado actual)</Text>
        <Text style={styles.previewText}>email: {form.email || "(vacío)"}</Text>
        <Text style={styles.previewText}>
          password: {form.password ? "********" : "(vacío)"}
        </Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0d1117",
    padding: 16,
    justifyContent: "center",
  },
  label: {
    color: "#c9d1d9",
    marginBottom: 6,
    fontWeight: "700",
  },
  input: {
    backgroundColor: "#161b22",
    borderColor: "#30363d",
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 10,
    paddingHorizontal: 12,
    color: "#c9d1d9",
    marginBottom: 12,
  },
  preview: {
    marginTop: 8,
    backgroundColor: "#161b22",
    borderColor: "#30363d",
    borderWidth: 1,
    borderRadius: 10,
    padding: 12,
  },
  previewTitle: {
    color: "#58a6ff",
    fontWeight: "800",
    marginBottom: 6,
  },
  previewText: {
    color: "#c9d1d9",
    opacity: 0.9,
    lineHeight: 20,
  },
});
