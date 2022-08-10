export interface VlcPlayerPlugin {
  stream(options: { channels: any[], idx: number }): Promise<{ channels: any[], idx: number }>;
}
