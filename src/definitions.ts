export interface VlcPlayerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
