import { useState } from "react";
import { StyleSheet, Text, TextInput, View } from "react-native";

export function promedio_notas() {
    const [notas, setNotas] = useState<{ nota1: string; nota2: string; nota3: string }>
    ({ nota1: "", nota2: "", nota3: "" });
    const [promedio, setPromedio] = useState<number | null>(null);

    const calcularPromedio = () => {
        const n1 = parseFloat(notas.nota1) || 0;
        const n2 = parseFloat(notas.nota2) || 0;
        const n3 = parseFloat(notas.nota3) || 0;
        const prom = (n1 + n2 + n3) / 3;
        setPromedio(prom);
    };

    return (
        <View style={styles.container}>
            <Text style={styles.label}>Nota 1</Text>
            <TextInput
                value={notas.nota1}
                onChangeText={(text) => setNotas((prev) => ({ ...prev, nota1: text }))}
                style={styles.input}
                keyboardType="numeric"
                placeholder="Ingrese la nota 1"
                placeholderTextColor="#8b949e"
            /> 
            <Text style={styles.label}>Nota 2</Text>
            <TextInput
                value={notas.nota2}
                onChangeText={(text) => setNotas((prev) => ({ ...prev, nota2: text }))}
                style={styles.input}
                keyboardType="numeric"
                placeholder="Ingrese la nota 2"
                placeholderTextColor="#8b949e"
            />
            <Text style={styles.label}>Nota 3</Text>
            <TextInput
                value={notas.nota3}
                onChangeText={(text) => setNotas((prev) => ({ ...prev, nota3: text }))}
                style={styles.input}
                keyboardType="numeric"
                placeholder="Ingrese la nota 3"
                placeholderTextColor="#8b949e"
            />
            <Text style={styles.button} onPress={calcularPromedio}>Calcular Promedio</Text>
            {promedio !== null && (
                <Text style={styles.result}>Promedio: {promedio.toFixed(2)}</Text>
            )}
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
  button: {
    backgroundColor: "#21262d",
    borderColor: "#58a6ff",
    borderWidth: 1,
    paddingVertical: 10,
    paddingHorizontal: 14,
    borderRadius: 10,
    color: "#58a6ff",
    fontWeight: "800",
    textAlign: "center",
    marginTop: 12,
  },
  result: {
    color: "#58a6ff",
    fontSize: 18,
    fontWeight: "700",
    marginTop: 16,
  },
});