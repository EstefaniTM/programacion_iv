import { StyleSheet, Text, View } from "react-native";
import { colors, spacing, radius } from "../theme/tokens";

export function TokenDemo() {
  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>Theme tokens</Text>
        <Text style={styles.body}>Colores y spacing centralizados.</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.bg,
    justifyContent: "center",
    padding: spacing.lg,
  },
  card: {
    backgroundColor: colors.card,
    borderColor: colors.border,
    borderWidth: 1,
    borderRadius: radius.md,
    padding: spacing.md,
  },
  title: {
    color: colors.primary,
    fontWeight: "800",
    fontSize: 16,
    marginBottom: spacing.xs,
  },
  body: {
    color: colors.text,
    opacity: 0.9,
    lineHeight: 20,
  },
});