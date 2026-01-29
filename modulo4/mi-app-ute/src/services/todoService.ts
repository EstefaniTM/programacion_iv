export type TodoDTO = {
  id: number;
  title: string;
};

export async function getTodos(limit = 8): Promise<TodoDTO[]> {
  const res = await fetch(`https://jsonplaceholder.typicode.com/todos?_limit=${limit}`);
  const data = (await res.json()) as TodoDTO[];
  return data;
}
